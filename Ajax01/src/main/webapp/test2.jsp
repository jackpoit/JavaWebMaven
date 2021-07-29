<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/29
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/ajax.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>异步请求</h2>
<input type="text" id="uname">
<input type="text" id="pwd">
<span id="login">测试异步登陆</span>
<hr>
<p>我是页面的其他数据</p>
<script>
    document.getElementById("login").onclick = function () {
        let uname = document.getElementById("uname").value;
        let pwd = document.getElementById("pwd").value;
        //2.发送ajax请求
        ajax({
            url:"http://localhost:8080/Ajax01/test",
            type:"post",
            data:"uname="+uname+"&pwd="+pwd,
            success:function (data) {
                if (data == "ok") {
                    alert("成功")
                } else if (data == "no")
                    alert("失败")
            }
        })
    }


</script>
</body>
</html>
