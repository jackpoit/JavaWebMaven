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
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin.js"></script>

    <script src="bootstrap/js/bootstrap.js"></script>
    <%--  实际上是 http://localhost:8080/Day04/page/admin/bootstrap/js/bootstrap.js  --%>
    <%--  肯定不成功--%>

</head>
<body>
<h1>管理员后台界面</h1>
<h2>
    <a href="<%=request.getContextPath()%>/welcome.jsp">返回首页1</a>
    <%--    <a href="welcome.jsp">返回首页2</a>  实际访问的是:http://localhost:8080/Day04/page/admin/welcome.jsp--%>

    <a href="../../welcome.jsp">返回首页2</a>

    <a href=" ${pageContext.request.contextPath}/page/user/user.jsp">进入用户页1</a>

    <a href="page/user/user.jsp">进入用户页2</a>
    <%--    不行 实际访问的是http://localhost:8080/Day04/page/admin/page/user/user.jsp--%>
    <%--    就是把 http://localhost:8080/Day04/page/admin/admin.jsp的 admin.jsp替换成page/user/user.jsp--%>

    <%=request.getContextPath()%>
    ${pageContext.request.contextPath}
    <ul class="nav">
        <li>1</li>
        <li>2</li>
        <li>3</li>
    </ul>
</h2>

</body>
</html>
