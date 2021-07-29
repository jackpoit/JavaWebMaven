<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/29
  Time: 16:20
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
            let pwdFlag = false;
            //1.确认密码添加blur事件
            $('#repwd').blur(function () {
                //1.1获取密码和确认密码
                let pwd = $('#pwd').val()
                let repwd = $(this).val()
                //1.2判断密码
                let $span = $('#pwdSpan');
                if (pwd != repwd && pwd != '') {
                    $span.html("&times;两次密码不一致");
                    $span.css({"color": "red"});
                    pwdFlag = false;
                } else {
                    $span.html("");
                    pwdFlag = true;
                }
            });
            //2.给按钮绑定点击事件(异步提交表单
            // $('#myForm').submit;// 同步提交:触发action+method
            $('#btn').click(function () {
                if (!pwdFlag) {
                    alert("请完善表单参数");
                    return;
                }

                // alert(typeof $('#myForm').serialize());  string类型

                alert($('#myForm').serialize());
                // 将表单所有加了name属性的input参数序列化成
                // 一个标准的application/x-www-form-urlencoded格式

                //使用ajax异步提交表单数据
                $.ajax({
                    url: "user",
                    type: "POST",
                    data: $('#myForm').serialize(),
                    dataType: "text",
                    success: function (text) {
                        if (text=="ok"){
                            $('#myModal').modal('show');
                        }
                    }
                })
            })
        })


    </script>
</head>
<body>

<h2>异步提交普通表单</h2>
<form id="myForm" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="m" value="doRegister">
    用户名:
    <input type="text" name="username" id="username"> <br>
    密码:
    <input type="password" name="password" id="pwd"> <br>
    确认密码:
    <input type="password" id="repwd">
    <span id="pwdSpan"></span>
    <br>
    性别:
    <input type="radio" name="gender" value="男">男
    <input type="radio" name="gender" value="女">女<br>
    课程:
    <input type="checkbox" name="course" value="体育">体育
    <input type="checkbox" name="course" value="化学">化学
    <input type="checkbox" name="course" value="物理">物理
    <input type="checkbox" name="course" value="数学">数学
    <br>
    城市:
    <select>
        <option name="city" value="010">北京</option>
        <option name="city" value="221" selected>上海</option>
        <option name="city" value="666">广州</option>
    </select>

    <button type="button" id="btn" class="btn btn-primary">提交</button>
    <%--type=submit是同步提交 触发其中的action+method--%>

</form>


<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
