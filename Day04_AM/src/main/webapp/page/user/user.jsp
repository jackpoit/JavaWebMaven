<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/20
  Time: 14:09
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
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-3.5.1.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="js/admin.js"></script>

</head>
<body>
<h1>用户页面</h1>
<ul class="nav">
    <li>1</li>
    <li>2</li>
    <li>3</li>
    <li>4</li>
</ul>

<h2>
    <%=request.getScheme()%>
    <%=request.getServerName()%>
    <%=request.getServerPort()%>
    <%=request.getContextPath()%>

    <%=basePath%>


</h2>
</body>
</html>
