<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/29
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>原生Ajax</title>
</head>
<body>

<h2>同步请求</h2>
<form action="test" enctype="application/x-www-form-urlencoded">
    <input type="text" name="uname">
    <input type="text" name="pwd">
    <button type="submit">提交</button>
</form>

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
        //异步登陆验证

        //1.创建原生Ajax请求对象
        let xhr = new XMLHttpRequest();//可以发送异步请求
        //2.配置请求方式+请求路由
        // xhr.open("get", "http://localhost:8080/Ajax01/test?uname=" + uname + "&pwd=" + pwd);
        xhr.open("post", "http://localhost:8080/Ajax01/test");
        //3.发送异步请求
        // xhr.send(); //get专用  请求参数在请求路由中

        //设置请求头
        xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xhr.send("uname=" + uname + "&pwd=" + pwd);//post专用  请求参数在请求体中
        //4.处理服务器响应回的数据
        xhr.onload = function () {
            //5.获取响应正文
            let data = xhr.responseText;
            if (data == "ok") {
                alert("成功")
            } else if (data == "no")
                alert("失败")
        }
    }


</script>

</body>
</html>
