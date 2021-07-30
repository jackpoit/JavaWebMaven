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
    <script src="js/page.js"></script>

</head>
<body>
<div class="container" id="showUser" style="display: none">
    <div class="container text-center" style="margin-top: 50px;min-height: 300px" >
        <table class="table-bordered table-hover text-center col-md-6 col-md-offset-3">
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
    </div>
    <div class="container text-center">
        <ul class="pagination" id="pageNav">
            <li><a  href="javascript:;" onclick="prePage()">上一页</a></li>
            <li><a  href="javascript:;" onclick="currentPage(1)">1</a></li>
            <li><a  href="javascript:;" onclick="currentPage(2)">2</a></li>
            <li><a  href="javascript:;" onclick="currentPage(3)">3</a></li>
            <li><a  href="javascript:;" onclick="nextPage()">下一页</a></li>
        </ul>
    </div>

</div>



</body>
</html>
