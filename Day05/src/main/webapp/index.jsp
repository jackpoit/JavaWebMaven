<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/21
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%--设置根路径--%>

    <title>文件上传</title>
</head>
<body>
<h2>Servlet3.0新特性文件上传</h2>
<%--
文件上传的准备工作
1.method="post"
2.enctype="multipart/form-data" 表单的提交采用二进制流的形式
3.提供 input file
--%>
<form action="upload" method="post" enctype="multipart/form-data">
    用户名: <input type="text" name="user"> <br>
    文件: <input type="file" name="myFile"> <br>
    <button type="submit">上传</button>
</form>
<img src="${imgPath}" alt="">
${imgPath}
</body>
</html>
