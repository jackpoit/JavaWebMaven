let priceFlag = false;
let stockFlag = false;
let curPage = 1;
let pageUpFlag = true;
$(function () {
    $('#price').blur(function () {
        let pricePattern = /^\d+(\.\d+)?$/;
        if ($(this).val() != "") {
            if (!pricePattern.test($(this).val())) {
                alert("价格输入不合法");
                $(this).val("");
                priceFlag = false;
            } else {
                priceFlag = true;
            }
        } else {
            priceFlag = false;
        }
    });
    $('#stock').blur(function () {
        let pricePattern = /^[0-9]+$/;
        if ($(this).val() != "") {
            if (!pricePattern.test($(this).val())) {
                alert("库存输入不合法");
                $(this).val("");
                stockFlag = false;
            } else {
                stockFlag = true;
            }
        } else {
            stockFlag = false;

        }

    });
    $('#searchSuccessModal').modal({
        backdrop: "static",
        keyboard: true,
        show: false  // 控制模态框是否直接显示 false 不显示
    });

    $('#addModal').modal({
        backdrop: "static",
        keyboard: true,
        show: false  // 控制模态框是否直接显示 false 不显示
    });

    $('#myTabs a:first').tab('show');


})

function delTr(obj) {
    $(obj).parent().parent().remove();

}

function searchById() {
    let id = prompt("请输入要查询的商品id");
    let idPattern = /^[1-9][0-9]*$/;
    if (idPattern.test(id)) {
        window.location.href = "/Exam0719/showById?id=" + id;
    } else {
        alert("id输入不合法");
    }
}

function searchFailed() {
    $('#searchFailedModal').modal('show');
    setTimeout(function () {
        $('#searchFailedModal').modal('hide')
    }, 2000)

}

function searchSuccess() {
    $('#searchSuccessModal').modal('show');
}

function showAddModal() {
    $('#addModal').modal('hide');
    $('#addResModal').modal('show');
    setTimeout(function () {
        $('#addResModal').modal('hide')
    }, 2000)

}

function addItemBtn() {
    if (priceFlag && stockFlag) {
        $('#addItem').submit();
    }
}

function getUserPage(Page) {
    curPage = Page;

}
asa
function getPagePath(obj) {
    if ($(obj).text() === '«') {
        if (curPage > 1) {
            curPage -= 1;
        } else {
            pageOneFailed();
            return;
        }
    } else if ($(obj).text() === '»') {
        console.log("flag" + pageUpFlag)
        if (pageUpFlag) {
            curPage += 1;
        } else {
            pageSearchFailed();
            return;
        }
    } else {
        curPage = $(obj).text();
    }
    window.location.href = "productPage?page=" + curPage + "&len=8";
}

function getFirstPage() {
    curPage = 1;
    window.location.href = "productPage?page=" + curPage + "&len=8";
}

function pageUpFlagFailed() {
    pageUpFlag = false;
    console.log(pageUpFlag);
    pageSearchFailed();
}

function pageSearchFailed() {
    $('#pageEmptyModal').modal('show');
    setTimeout(function () {
        $('#pageEmptyModal').modal('hide')
    }, 2000);
    console.log(111);
    // pageUpFlag = false;
}

function pageOneFailed() {
    $('#pageOneModal').modal('show');
    setTimeout(function () {
        $('#pageOneModal').modal('hide')
    }, 2000)
}