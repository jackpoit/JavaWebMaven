<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/29
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--设置根路径--%>
    <%
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="js/jquery-3.5.1.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script>

        $(function () {
            $('#showAll').click(function () {
                //发送ajax请求去查询所有用户
                $.ajax({
                    url: "user",
                    type: "get",
                    data: {m: "showAll"},
                    dataType: "json",
                    //text类型 ajax直接接收字符串类型
                    success: function (list) {
                        // let parseJson=JSON.parse(list)
                        // 将json字符串转换成json对象

                        //服务器发送的是json字符串,但是jQuery帮我们转换成了json对象
                        let trs = "";
                        for (let user of list) {
                            trs += "<tr><td>" + user.id + "</td>" +
                                "<td>" + user.username + "</td>" +
                                "<td><img src='" + user.imagePath + "' width='50px'></td>" +
                                "<td>" + user.phone + "</td>" +
                                "<td>" + user.email + "</td>" +
                                "<td>" + user.userLevel + "</td></tr>";

                        }
                        $('#content').html(trs);
                        $('#tb_user').fadeIn(2000);
                    }
                })
            })
        })
    </script>

</head>
<body>

<div class="container text-center">
    <span id="showAll" class="btn btn-primary">查询所有</span>
    <hr>
    <table class="table-bordered table-hover text-center col-md-6 col-md-offset-3" id="tb_user" style="display: none">
        <tr class="text-center">
            <td>id</td>
            <td>用户名</td>
            <td>头像</td>
            <td>手机号</td>
            <td>邮箱</td>
            <td>等级</td>
        </tr>
        <tbody id="content"></tbody>
    </table>
    <div>
        <ul class="pagination">
            <li><a href="">上一页</a></li>
            <li><a href="">1</a></li>
            <li><a href="">2</a></li>
            <li><a href="">3</a></li>
            <li><a href="">下一页</a></li>
        </ul>
    </div>
</div>


</body>
</html>
