$(function () {

    load();
})

function load() {
    currentPage(1, "-1", "-1", "-1");
}

let totalPage = 0;
let aName = '-1';
let aType = '-1';
let aPlat = '-1';

function currentPage(num, name, type, plat) {

    $.ajax({
        url: "app",
        type: "post",
        data: {m: "showByKeyword", currentPage: num, name: name, type: type, plat: plat},
        dataType: "json",
        success: function (info) {
            totalPage = info.pages;

            let trs = "";
            for (let app of info.list) {
                let count = 0;
                if (app.hasOwnProperty("appCount")) {
                    count = app.appCount;
                }
                trs += "<tr>\n" +
                    "\t\t\t\t\t\t\t\t<td>选择<input type='checkbox' class='sel' name='sel' value='" + app.id + "'></td>\n" +
                    "\t\t\t\t\t\t\t\t<td>" + app.id + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>" + app.appName + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>" + app.appSize + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>" + app.appType + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>" + app.appPlatform + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>" + count + "次</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t\t\t\t<img src='" + app.appImg + "'  width='50px'>\n" +
                    "\t\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t\t\t\t<button class='btn btn-danger' type='button' onclick='deletePros(" + app.id + ")'>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<span class='glyphicon glyphicon-remove'></span>删除\n" +
                    "\t\t\t\t\t\t\t\t\t</button>\n" +
                    "\t\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t\t\t\t<button   type='button' class='btn btn-primary' onclick='updateApp(" + app.id + ")' >\n" +
                    "\t\t\t\t\t\t\t\t\t\t<span class='glyphicon glyphicon-edit' ></span>修改\n" +
                    "\t\t\t\t\t\t\t\t\t</button>\n" +
                    "\t\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t</tr>"
            }
            $('#content').html(trs);
            let lis = "<li><a href='javascript:;' onclick='prePage(" + info.pageNum + ",\"" + aName + "\",\"" + aType + "\",\"" + aPlat + "\")'>上一页</a></li>";
            for (let i = 1; i <= info.pages; i++) {
                if (i == info.pageNum) {
                    lis += "<li class='active'><a href='javascript:;'>" + i + "</a></li>"
                } else {
                    lis += "<li><a href='javascript:;' onclick=' currentPage(" + i + ",\"" + aName + "\",\"" + aType + "\",\"" + aPlat + "\")'>" + i + "</a></li>"
                }
            }
            lis += "<li><a href='javascript:;' onclick='nextPage(" + info.pageNum + ",\"" + aName + "\",\"" + aType + "\",\"" + aPlat + "\")'>下一页</a></li>";
            $('#oriPageNav').html(lis);
            checkBoxClick();
        }
    });
}

function checkBoxClick() {
    let $products = $('input[name="sel"]');
    let total = $('input[name="sel"]').length;

    //全选全消
    $('#all').click(function () {
        let flag = this.checked;
        $products.prop("checked", flag);
    });
    $products.click(function () {
        let count = $('input[name="sel"]').filter(":checked").length;
        $('#all').prop("checked", count === total);
    });
}

//删除
function deleteAjax(ids) {
    $.ajax({
        url: "app",
        type: "post",
        traditional: "true",//数组选项
        data: {m: "deleteByIds", ids: ids},
        dataType: "text",
        success: function (text) {
            if ("Y" == text) {
                alert("删除成功")
                currentPage(1, aName, aType, aPlat);
            } else if ("N" == text) {
                alert("删除失败")
            }
        }
    })
}

function deletePros(id) {

    let flag = confirm("您确认要删除" + id + "号app吗?");
    if (flag) {
        deleteAjax(id);
    }
}

function deleteAll() {
    let $ids = $('input[name="sel"]').filter(":checked")
    if ($ids.length === 0) {
        alert("没有选择的app")
        return;
    }
    let ids = [];
    let idStr = "";
    for (let i = 0; i < $ids.length; i++) {
        ids[i] = $ids.eq(i).val();
        idStr += ids[i];
        if (i != $ids.length - 1) {
            idStr += ",";
        }
    }
    let flag = confirm("您确认要删除" + idStr + "号app吗?");
    if (flag) {
        deleteAjax(ids);
    }
}

//ajax查询上一页
function prePage(num, ono, status, pname) {
    if (num == 1) {
        currentPage(totalPage, ono, status, pname)
    } else {
        currentPage(num - 1, ono, status, pname)
    }
}

//ajax查询下一页
function nextPage(num, ono, status, pname) {
    if (num == totalPage) {
        currentPage(1, ono, status, pname)
    } else {
        currentPage(num + 1, ono, status, pname)
    }
}

//添加
$(function () {
    //搜索按钮
    $('#appSearch').click(function () {
        let seaName = $('#seaName').val()
        aName = seaName == '' ? "-1" : seaName
        aType = $('#seaType option:selected').val()
        aPlat = $('#seaPlat option:selected').val()
        currentPage(1, aName, aType, aPlat);
    })

})

$(function () {
    // 1. 头像预览
    $('#addAppImg').change(function () {
        let file = this.files[0];
        let imgPattern = /image\/\w+/;// 用来匹配以 image/
        if (!imgPattern.test(file.type)) {
            alert("文件必须为图片！");
            imgFlag = false;
            addFlag = false;
            return false;
        }
        let reader = new FileReader(); // 创建文件预览器
        reader.readAsDataURL(file);
        reader.onload = function () {
            $('#addImg').html("<img src=" + this.result + " width='100px';height='100px' >");
            $('#addImg').slideDown(1000);
        }
    });
});

function addApp() {
    $.ajax({
        url: "app",
        type: "post",
        data: new FormData($('#addFormId')[0]),
        contentType: false,
        processData: false,
        dataType: "text",
        success: function (text) {
            if ("Y" == text) {
                alert("添加成功")
                currentPage(1, aName, aType, aPlat);
            } else if ("N" == text) {
                alert("添加失败")
            }
            $('#addModal').modal('hide')
        }
    })

}

function updateApp(id) {
    $('#updateModal').modal('show');
    $('#id').val(id)

}

function updateAjax() {
    $.ajax({
        url: "app",
        type: "post",
        data: new FormData($('#updateFormId')[0]),
        contentType: false,
        processData: false,
        dataType: "text",
        success: function (text) {
            if ("Y" == text) {
                alert("修改成功")
                currentPage(1, aName, aType, aPlat);
            } else if ("N" == text) {
                alert("修改失败")
            }
            $('#updateModal').modal('hide')
        }
    })
}