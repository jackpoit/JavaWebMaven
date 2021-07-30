let totalPage = 0;
$(function () {
    Load();
});

function Load() {
    currentPage(1);
}

//查询当前页码的核心ajax逻辑
function currentPage(num) {
    $.ajax({
        url: "user",
        type: "get",
        data: {m: "page", currentPage: num},
        dataType: "json",
        //text类型 ajax直接接收字符串类型
        success: function (info) {
            totalPage = info.pages;
            let trs = "";
            for (let user of info.list) {
                trs += "<tr><td>" + user.id + "</td>" +
                    "<td>" + user.username + "</td>" +
                    "<td><img src='" + user.imagePath + "' width='50px'></td>" +
                    "<td>" + user.phone + "</td>" +
                    "<td>" + user.email + "</td>" +
                    "<td>" + user.userLevel + "</td></tr>";
            }
            $('#content').html(trs);


            let lis="<li><a href='javascript:;' onclick='prePage("+info.pageNum+")'>上一页</a></li>";
            for (let i = 1; i <=info.pages; i++) {
                if (i==info.pageNum){
                    lis+="<li class='active'><a href='javascript:;'>"+i+"</a></li>"
                }else {
                    lis+="<li><a href='javascript:;' onclick='currentPage("+i+")'>"+i+"</a></li>"
                }
            }
            lis+="<li><a href='javascript:;' onclick='nextPage("+info.pageNum+")'>下一页</a></li>";
            $('#pageNav').html(lis);

            $('#showUser').fadeIn(2000);
        }
    });
}

//ajax查询上一页
function prePage(num) {
    if (num == 1) {
        currentPage(totalPage)
    }else {
        currentPage(num-1)
    }
}


//ajax查询下一页
function nextPage(num) {
    if (num == totalPage) {
        currentPage(1)
    }else {
        currentPage(num+1)
    }
}