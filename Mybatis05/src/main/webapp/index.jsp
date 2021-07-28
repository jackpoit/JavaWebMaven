<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/28
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>分页</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row text-center">
        <h2 class="text-center">员工分页展示</h2>
        <div class="col-md-6 col-md-offset-3">
            <form action="page" method="get">
                <input type="text" name="keyword" style="height: 40px">
                <button type="submit" class="btn btn-primary" style="height: 40px">查询</button>
            </form>

        </div>
    </div>
    <div class="row">
        <table class="table-hover table-bordered text-center" style="line-height: 60px;width: 100%">
            <tr style="background: #666;color: #ff6670;font-weight: bold">
                <td>编号</td>
                <td>工号</td>
                <td>姓名</td>
                <td>头像</td>
                <td>性别</td>
                <td>生日</td>
                <td>职位</td>
                <td>薪资</td>
            </tr>
            <c:forEach var="emp" items="${info.list}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.tno}</td>
                    <td>${emp.name}</td>
                    <td><img src="${emp.imagePath}" style="width: 60px"></td>
                    <td>${emp.gender}</td>
                    <td><fmt:formatDate value="${emp.birthday}"/></td>
                    <td>${emp.title}</td>
                    <td>${emp.salary}</td>
                </tr>
            </c:forEach>

        </table>

    </div>
    <div class="row text-center" style="min-height: 368px">
        <ul class="pagination">
            <li><a href="page?currentPage=${info.prePage}&keyword=${kw}">上一页</a></li>

            <%--        <c:if test="${info.pageNum==1}">--%>
            <%--            <li><a href="page?currentPage=${info.pages}&keyword=${kw}">上一页</a></li>--%>
            <%--        </c:if>--%>
            <%--        <c:if test="${info.pageNum!=1}">--%>
            <%--            <li><a href="page?currentPage=${info.prePage}&keyword=${kw}">上一页</a></li>--%>
            <%--        </c:if>--%>
            <c:forEach var="page" begin="1" end="${info.pages}">
                <c:if test="${page==info.pageNum}">
                    <li class="active"><a href="javascript:;">${page}</a></li>
                </c:if>
                <c:if test="${page!=info.pageNum}">
                    <li><a href="page?currentPage=${page}&keyword=${kw}">${page}</a></li>
                </c:if>
            </c:forEach>

            <%--        <c:if test="${info.pageNum==info.pages}">--%>
            <%--            <li><a href="page?currentPage=1&keyword=${kw}">下一页</a></li>--%>
            <%--        </c:if>--%>
            <%--        <c:if test="${info.pageNum!=info.pages}">--%>
            <%--            <li><a href="page?currentPage=${info.nextPage}&keyword=${kw}">下一页</a></li>--%>
            <%--        </c:if>--%>
            <li><a href="page?currentPage=${info.nextPage}&keyword=${kw}">下一页</a></li>

        </ul>
    </div>

</div>


</body>
</html>
