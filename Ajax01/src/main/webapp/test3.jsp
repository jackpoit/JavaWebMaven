<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/29
  Time: 14:05
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
            //1.uname写失去焦点的事件
            $('#uname').blur(function () {
                //1.前端验证
                let uname=$(this).val(); //获取输入框中的内容
                let unamePattern=/[a-zA-Z][a-zA-Z0-9]*/;
                if(!unamePattern.test(uname)){
                    alert("请输入正确的用户名");
                    return;
                }
                //1.2发送Ajax异步请求到服务器去验证用户名在数据库是否真实存在
                //流程: Ajax->Servlet->Service->Mapper(Mybatis)
                // //{m:"checkUser",uname:uname}
                // $.get("user","m=checkUser&uname="+uname,function (text) {
                //     let $span=$('#unameSpan');
                //     //text:响应正文
                //     if (text=="ok"){
                //         $span.html("&times;用户名已存在")
                //         $span.css({"color":"red"})
                //     }else if (text=="no"){
                //         $span.html("√")
                //         $span.css({"color":"green"})
                //     }
                // },"text")

                $.ajax({  //ajax参数只有一个-->js对象:其中有若干属性
                    url:"user",      //路由
                    type:"GET",      //请求方式GET POST
                    // data:"m=checkUser&uname="+uname,  //请求参数
                    data:{m:"checkUser",uname:uname},
                    dataType:"text" ,  //预期的服务器响应类型
                    success:function (text) { //服务器响应成功后执行的回调函数
                        let $span=$('#unameSpan');
                        //text:响应正文
                        if (text=="ok"){
                            $span.html("&times;用户名已存在")
                            $span.css({"color":"red"})
                        }else if (text=="no"){
                            $span.html("√")
                            $span.css({"color":"green"})
                        }
                    }
                });
            });
        })
    </script>
</head>
<body>

<h2>基于jQuery的Ajax</h2>
<hr>
<h2>输入框失去焦点-->验证用户名是否存在</h2>
<input type="text" name="uname" id="uname">
<span id="unameSpan"></span>


<hr>
<h2>异步提交普通表单</h2>
<form action="" id="myForm">
    <label for="username">用户名</label>
    <input type="text" name="username" id="username"> <br>
    <label for="password">密码</label>
    <input type="password" name="password" id="password"> <br>
    <button type="button" id="btn">提交</button>
<%--type=submit是同步提交 触发其中的action    --%>
</form>


<hr>
<h2>异步提交带文件上传的表单</h2>

</body>
</html>
