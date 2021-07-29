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

    <script>
        $(function () {
            //给按钮绑定一个点击事件
            $('#btn').click(function () {
                // let formData=new FormData($('#myForm')[0]);
                // formData.set("m","userImg");
                // formData.set("uname","admin");
                $.ajax({
                    url: "upload",
                    type: "POST",
                    data: new FormData($('#myForm')[0]),
                    dataType: "text",
                    contentType: false,
                    processData: false,
                    success: function (text) {
                        $('#showBox').html("<img src='" + text + "' width='100px'>")
                    }
                })
            })

        })

    </script>
</head>
<body>

<h2>异步提交普通表单</h2>
<div id="showBox"></div>

<form id="myForm">

    <input type="hidden" name="m" value="userImg">
    用户名:
    <input type="text" name="username" id="username"> <br>
    文件:
    <input type="file" name="myFile"><br>

    <button type="button" id="btn">提交</button>

</form>


</body>
</html>
