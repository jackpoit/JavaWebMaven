<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rua
  Date: 2021/7/20
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script src="js/index.js"></script>

</head>
<body>
<h2 class="text-center">员工管理系统</h2>
<div class="container" style="margin-top: 30px">
    <form action="showByKeyword" class="form-inline text-center" method="get">
        <div class="form-group">
            <label class="sr-only" for="keyword">keyword</label>
            <input type="text" id="keyword" name="keyword" class="form-control" placeholder="keyword">
        </div>
        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>搜索</button>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addModal" >
            <span class="glyphicon glyphicon-plus"></span>添加</button>
    </form>
</div>
<c:if test="${!empty empList}">
    <div class="container" style="margin-top: 20px">
        <div class="row">
            <div class="col-md-10 col-md-offset-1" style="min-height: 700px">
                <table class="table-bordered table-hover text-center"
                       style="font-size: 18px;line-height: 50px;width: 100%">
                    <tr style=" background-color:#999; color: #ffff00;font-weight: bold">
                        <td>编号</td>
                        <td>工号</td>
                        <td>姓名</td>
                        <td>性别</td>
                        <td>生日</td>
                        <td>职位</td>
                        <td>薪资</td>
                        <td>领导编号</td>
                        <td>部门编号</td>
                        <td colspan="2">操作</td>
                    </tr>
                    <c:forEach var="emp" items="${empList}">
                        <tr>
                            <td>${emp.id}</td>
                            <td>${emp.tno}</td>
                            <td>${emp.name}</td>
                            <td>${emp.gender}</td>
                                <%--                    <td>${emp.birthday}</td>--%>
                            <td><fmt:formatDate value="${emp.birthday}" pattern="yyyy/MM/dd"/>
                            </td>
                            <td>${emp.title}</td>
                            <td>${emp.salary}</td>
                                <%--emp也是在域中的 可以用empty判断--%>
                            <td>${empty emp.managerId?"boss":emp.managerId}</td>
                            <td>${empty emp.deptId?"轮岗":emp.deptId}</td>
                            <td><a href="javascript:;" onclick="editItem(this)" class="btn btn-primary">
                                <span class="glyphicon glyphicon-edit"></span>修改</a></td>
                            <td>
                                <a href="javascript:deleteEmp(${emp.id})" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove"></span>删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="container row">
            <ul class="pager">
                <li><a href="javascript:;" onclick=" getPagePath(this)">&laquo;</a></li>
                <li><a href="javascript:;" onclick=" getPagePath(this)">1</a></li>
                <li><a href="javascript:;" onclick=" getPagePath(this)">2</a></li>
                <li><a href="javascript:;" onclick=" getPagePath(this)">3</a></li>
                <li><a href="javascript:;" onclick=" getPagePath(this)">4</a></li>
                <li><a href="javascript:;" onclick=" getPagePath(this)">&raquo;</a></li>
            </ul>
        </div>
    </div>

</c:if>
<c:if test="${empty empList}">
    <h2 class="text-center" style="color: #b0b0b0">还未帮您查询到任何数据</h2>
</c:if>
<!--编辑模态框-->
<div class="modal fade" id="editModal" tabindex="-1">
    <div class="modal-dialog" style="margin-top: 150px">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h4 class="modal-title text-left">修改用户</h4>
            </div>
            <div class="modal-body">
                <form action="edit" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label for="tno" class="control-label col-md-2 ">工号:</label>
                        <div class="col-md-9">
                            <input type="text" id="tno" name="tno" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="control-label col-md-2">姓名:</label>
                        <div class="col-md-9">
                            <input type="text" id="name" name="name" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="gender" class="control-label col-md-2">性别:</label>
                        <div class="col-md-9">
                            <input type="text" id="gender" name="gender" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="control-label col-md-2">生日:</label>
                        <div class="col-md-9">
                            <input type="text" id="birthday" name="birthday" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="control-label col-md-2">职位:</label>
                        <div class="col-md-9">
                            <input type="text" id="title" name="title" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="salary" class="control-label col-md-2">薪资:</label>
                        <div class="col-md-9">
                            <input type="text" id="salary" name="salary" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="managerId" class="control-label col-md-2">领导编号:</label>
                        <div class="col-md-9">
                            <input type="text" id="managerId" name="managerId" class="form-control" pattern="[1-9]\d*|"
                                   title="请输入正确格式">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="deptId" class="control-label col-md-2">部门编号:</label>
                        <div class="col-md-9">
                            <input type="text" id="deptId" name="deptId" class="form-control" pattern="[1-9]\d*|"
                                   title="请输入正确格式">
                        </div>
                    </div>
                    <div class="form-group sr-only">
                        <label for="id" class="control-label col-md-2">ID:</label>
                        <div class="col-md-9">
                            <input type="text" id="id" name="id" class="form-control">

                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-6 col-md-offset-3 text-center">
                            <button type="submit" class="btn btn-primary">提交修改</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--添加模态框-->
<div class="modal fade" id="addModal" tabindex="-1">
    <div class="modal-dialog" style="margin-top: 150px">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h4 class="modal-title text-left">添加用户</h4>
            </div>
            <div class="modal-body">
                <form action="add" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label for="add_tno" class="control-label col-md-2">工号:</label>
                        <div class="col-md-9">
                            <input type="text" id="add_tno" name="tno" class="form-control"
                                   required pattern="WNSH[0-9]{4}" title="工号格式如下:WNSH0001">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_name" class="control-label col-md-2">姓名:</label>
                        <div class="col-md-9">
                            <input type="text" id="add_name" name="name" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">性别:</label>

                            <label class="radio-inline" style="margin-left: 10px">
                                <input type="radio" value="男" name="gender">男

                            </label>
                        <label class="radio-inline">
                            <input type="radio" value="女" name="gender">女
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="add_birthday" class="control-label col-md-2">生日:</label>
                        <div class="col-md-9">
                            <input type="date" id="add_birthday" name="birthday" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_title" class="control-label col-md-2">职位:</label>
                        <div class="col-md-9">
                            <input type="text" id="add_title" name="title" class="form-control" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_salary" class="control-label col-md-2">薪资:</label>
                        <div class="col-md-9">
                            <input type="text" id="add_salary" name="salary" class="form-control"
                                   required pattern="\d+|\d+\.\d+" title="薪资必须是数字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_managerId" class="control-label col-md-2">领导编号:</label>
                        <div class="col-md-9">
                            <input type="text" id="add_managerId" name="managerId" class="form-control" pattern="[1-9]\d*|"
                                   title="请输入正确格式" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_deptId" class="control-label col-md-2">部门编号:</label>
                        <div class="col-md-9">
                            <input type="text" id="add_deptId" name="deptId" class="form-control" pattern="[1-9]\d*|"
                                   title="请输入正确格式" required>
                        </div>
                    </div>
                    <div class="form-group sr-only">
                        <label for="add_id" class="control-label col-md-2">ID:</label>
                        <div class="col-md-9">
                            <input type="text" id="add_id" name="id" class="form-control">
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-6 col-md-offset-3 text-center">
                            <button type="submit" class="btn btn-primary">确认添加</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
