<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--逻辑--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%--格式转换--%>
<html>
<head>
    <%--生产环境中需要配置资源访问的绝对路径--%>
    <% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
    <base href="<%=basePath%>">
    <title>员工管理系统</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-3.5.1.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="js/index.js"></script>
</head>
<body>
<%--标题--%>
<h1 class="text-center">HR 员工管理系统(模糊分页查询)</h1>

<%--模糊搜索--%>
<div class="container" style="margin-top: 30px">
    <form class="form-inline text-center" action="page" method="get">
        <div class="form-group">
            <label class="sr-only" for="kw">KeyWord</label>
            <input type="text" class="form-control" id="kw" name="keyword" placeholder="keyword">
        </div>
        <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-search"></span> 搜索
        </button>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addModal">
            <span class="glyphicon glyphicon-plus"></span> 添加
        </button>
    </form>
</div>

<%--结果展示区--%>
<div class="container" style="margin-top: 30px">
    <c:if test="${empty info.list}">
        <h2 class="text-center" style="color: #b0b0b0">还未帮您查询到任何数据!</h2>
    </c:if>
    <c:if test="${!empty info.list}">
        <%--商品展示区--%>
        <div class="col-md-12" style="min-height: 310px">
            <table class="table-bordered table-hover text-center" style="font-size: 18px;line-height:50px;width: 100%;">
                <tr style="background:#999;color: #fffa81;font-weight: bold">
                    <td>编号</td>
                    <td>工号</td>
                    <td>姓名</td>
                    <td>头像</td>
                    <td>性别</td>
                    <td>生日</td>
                    <td>职位</td>
                    <td>薪资</td>
                    <td>直属编号</td>
                    <td>部门编号</td>
                    <td colspan="3">操作</td>
                </tr>
                <c:forEach var="emp" items="${info.list}">
                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.tno}</td>
                        <td>${emp.name}</td>
                        <td><img src="${emp.imagePath}" width="50px" height="50px" class="img-circle" alt=""></td>
                        <td>${emp.gender}</td>
                        <td><fmt:formatDate value="${emp.birthday}" pattern="yyyy-MM-dd"/></td>
                        <td>${emp.title}</td>
                        <td>${emp.salary}</td>
                        <td>${empty emp.managerId?"BOSS":emp.managerId}</td>
                        <td>${empty emp.deptId?'轮岗':emp.deptId}</td>
                        <td>
                            <button class="btn btn-primary" onclick="editItem(this);">
                                <span class="glyphicon glyphicon-edit"></span> 编辑
                            </button>
                        </td>
                        <td>
                            <a href="javascript:deleteEmp(${emp.id})" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove"></span> 删除
                            </a>
                        </td>
                        <td>
                            <button onclick="uploadImg(this);" class="btn btn-success">
                                <span class="glyphicon glyphicon-upload"></span> 头像上传
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <%--分页导航--%>
        <c:if test="${info.pages>1}">
            <div class="col-md-12 text-center">
                <ul class="pagination">
                        <%--处理上一页逻辑--%>
                    <li><a href="page?currentPage=${info.prePage}&keyword=${kw}">上一页</a></li>

                        <%--处理中间页码的逻辑--%>
                    <c:forEach var="i" begin="1" end="${info.pages}">
                        <c:if test="${info.pageNum == i}">
                            <li class="active"><a href="javascript:;">${i}</a></li>
                        </c:if>
                        <c:if test="${info.pageNum != i}">
                            <li><a href="page?currentPage=${i}&keyword=${kw}">${i}</a></li>
                        </c:if>
                    </c:forEach>

                        <%--处理下一页的逻辑--%>
                    <li><a href="page?currentPage=${info.nextPage}&keyword=${kw}">下一页</a></li>
                </ul>
            </div>
        </c:if>
    </c:if>
    <%--跳转功能--%>
    <div class="col-md-8 col-md-offset-2 text-center" style="font-size: 18px; color: #b0b0b0">
        共<span style="color: #ff6700">${info.total}</span>条数据，
        目前在第<span style="color: #ff6700">${info.pageNum}</span>页
        <span style="margin-left: 40px">前往</span>
        <input type="text" class="text-center" id="pageNum" style="width: 60px;">
        <button class="btn btn-warning" onclick="goto()">跳转</button>
    </div>
</div>

<%--编辑的模态框--%>
<div class="modal fade" tabindex="-1" id="editModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-center">员工编辑</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <form class="form-horizontal" action="edit" method="post">
                            <input type="hidden" name="eid" id="eid">
                            <div class="form-group">
                                <label for="tno" class="col-sm-3 control-label">工号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="tno" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="ename" class="col-sm-3 control-label">姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="ename" name="ename" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gender" class="col-sm-3 control-label">性别</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="gender" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthday" class="col-sm-3 control-label">生日</label>
                                <div class="col-sm-9">
                                    <input type="date" class="form-control" id="birthday" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="title" class="col-sm-3 control-label">职位</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="title" name="title" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="salary" class="col-sm-3 control-label">薪资</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="salary" name="salary" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="managerId" class="col-sm-3 control-label">直属领导</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="managerId" name="managerId" required
                                           pattern="([1-9]\d*)|(BOSS)" title="格式错误">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="deptId" class="col-sm-3 control-label">部门代号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="deptId" name="deptId" required
                                           pattern="([1-9]\d*)|(\u8f6e\u5c97)" title="格式错误">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-primary btn-block">更新</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--添加的模态框--%>
<div class="modal fade" tabindex="-1" id="addModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-center">员工添加</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <form class="form-horizontal" action="add" method="post">
                            <div class="form-group">
                                <label for="add_tno" class="col-sm-3 control-label">工号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="add_tno" name="tno" required
                                           pattern="WNSH[0-9]{4}" title="格式如下：WNSH0001">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_name" class="col-sm-3 control-label">姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="add_name" name="name" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别</label>
                                <div class="radio col-sm-9">
                                    <label style="margin-right: 20px">
                                        <input type="radio" value="男" name="gender" checked> 男
                                    </label>
                                    <label>
                                        <input type="radio" value="女" name="gender"> 女
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_birthday" class="col-sm-3 control-label">生日</label>
                                <div class="col-sm-9">
                                    <input type="date" class="form-control" id="add_birthday" name="birthday" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_title" class="col-sm-3 control-label">职位</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="add_title" name="title" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_salary" class="col-sm-3 control-label">薪资</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="add_salary" name="salary" required
                                           pattern="[1-9]\d*" title="薪资必须是数字">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_managerId" class="col-sm-3 control-label">直属领导</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="add_managerId" name="managerId" required
                                           pattern="([1-9]\d*)|(BOSS)" title="格式错误">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_deptId" class="col-sm-3 control-label">部门代号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="add_deptId" name="deptId" required
                                           pattern="([1-9]\d*)|(\u8f6e\u5c97)" title="格式错误">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-primary btn-block">确认添加</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--文件上传的模态框--%>
<div class="modal fade" tabindex="-1" id="uploadModal">
    <div class="modal-dialog" style="width: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-center">头像上传</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form action="upload" method="post" enctype="multipart/form-data" class="form-horizontal"
                          id="uploadForm">
                        <input type="hidden" id="empid" name="eid">
                        <input type="hidden" id="emptno" name="etno">
                        <div class="form-group">
                            <label for="empname" class="control-label col-md-2 col-md-offset-1">姓名</label>
                            <div class="col-md-8">
                                <input type="text" id="empname" class="form-control" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="myImg" class="control-label col-md-2 col-md-offset-1">头像</label>
                            <div class="col-md-8">
                                <input type="file" name="myImg" id="myImg" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div id="showImg" class="col-md-6 col-md-offset-4"
                                 style="width: 150px;height: 150px;"></div>
                        </div>
                        <div class="form-group text-center">
                            <div class="col-md-7 col-md-offset-3">
                                <button type="button" class="btn btn-primary btn-block" id="btn_upload">开始上传</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--跳转的脚本--%>
<script>
    function goto() {
        let totalPage = ${info.pages}; // 最后一页的页码
        let pageNum = $('#pageNum').val(); // 获取跳转的页码
        let numPattern = /[1-9]\d*/;
        if (!numPattern.test(pageNum)) {
            alert("您输入的页码是非法的");
            $('#pageNum').val("");
            return;
        }
        if (pageNum < 1 || pageNum > totalPage) {
            alert("您输入的页码超出范围[" + 1 + "," + totalPage + "]");
            $('#pageNum').val("");
            return;
        }
        //发送请求并查询结果
        location.href = "page?currentPage=" + pageNum + "&keyword=${kw}";
    }
</script>
</body>
</html>
