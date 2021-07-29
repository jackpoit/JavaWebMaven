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
                    success: function (list) {
                        //服务器发送的是json字符串,但是jQuery帮我们转换成了json对象
                        for (let user of list) {
                            $('#content').append(
                                "<tr><td>" + user.id + "</td>" +
                                "<td>" + user.username + "</td>" +
                                "<td><img src='" + user.imagePath + "' width='50px'></td>" +
                                "<td>" + user.phone + "</td>" +
                                "<td>" + user.email + "</td>" +
                                "<td>" + user.userLevel + "</td></tr>"
                            )
                        }
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
    <table class="table-bordered table-hover text-center col-md-6 col-md-offset-3">

        <tr class="text-center">
            <td>id</td>
            <td>用户名</td>
            <td>头像</td>
            <td>手机号</td>
            <td>邮箱</td>
            <td>等级</td>
        </tr>
        <tbody id="content">

        </tbody>
    </table>
</div>


</body>
</html>
