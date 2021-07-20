<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台登录</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-3.5.1.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>

</head>
<body style="background: url('imges/loginBG.JPG') no-repeat center ;background-size: 100% 100%">

<div class="container col-md-6 col-md-offset-3">
    <div class="container col-md-10 col-md-offset-1"
         style="margin-top: 200px;opacity: 0.8;background-color:#e0e0e0;padding: 0">
        <div class="text-center"
             style="background:#333333;font-size: 28px;height: 80px;line-height: 80px;color: #FFFFFF">
            <span class="glyphicon glyphicon-send"></span> 蜗牛商城后台登录系统
        </div>
        <div class="col-md-8 col-md-offset-2 text-center" style="padding: 40px 0;">
            <form action="" class="form-horizontal">
                <div class="form-group">
                    <label class="sr-only" for="username"></label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                        <input type="text" id="username" name="username" class="form-control">
                    </div>
                </div>
                <div class="form-group"></div>
                <div class="form-group">
                    <label class="sr-only" for="pwd"></label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                        <input type="text" id="pwd" name="pwd" class="form-control">
                    </div>
                </div>
                <div class="form-group"></div>
                <div class="form-group ">
                    <div class="col-md-6 col-md-offset-3 ">
                        <button type="submit" class="btn btn-primary btn-block">Login</button>
                        <a href="admin.jsp" class="btn btn-primary btn-block">Login</a>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
