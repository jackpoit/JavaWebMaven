<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%--逻辑--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <%--格式转换--%>
<html>
  <head>
      <%--生产环境中需要配置资源访问的绝对路径--%>
      <% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
      <base href="<%=basePath%>">
      <title>员工管理系统</title>
      <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
      <script src="bootstrap/js/jquery-3.5.1.js"></script>
      <script src="bootstrap/js/bootstrap.js"></script>
      <script src="js/index.js"></script>
  </head>
  <body>
      <%--标题--%>
      <h1 class="text-center">HR 员工管理系统 ${msg}</h1>

      <%--模糊搜索--%>
      <div class="container" style="margin-top: 30px">
          <form class="form-inline text-center" action="search" method="get">
              <div class="form-group">
                  <label class="sr-only" for="kw">KeyWord</label>
                  <input type="text" class="form-control" id="kw" name="keyword" placeholder="keyword">
              </div>
              <button type="submit" class="btn btn-primary">
                  <span class="glyphicon glyphicon-search"></span> 搜索</button>
          </form>
      </div>

      <%--结果展示区--%>
      <div class="container" style="margin-top: 30px">
          <c:if test="${empty empList}">
                <h2 class="text-center" style="color: #b0b0b0">还未帮您查询到任何数据!</h2>
          </c:if>
          <c:if test="${!empty empList}">
            <div class="col-md-12">
              <table class="table-bordered table-hover text-center" style="font-size: 18px;line-height:50px;width: 100%">
                <tr style="background:#999;color: #fffa81;font-weight: bold">
                  <td>编号</td>
                  <td>工号</td>
                  <td>姓名</td>
                  <td>性别</td>
                  <td>生日</td>
                  <td>职位</td>
                  <td>薪资</td>
                  <td>直属编号</td>
                  <td>部门编号</td>
                  <td colspan="2">操作</td>
                </tr>
                  <c:forEach var="emp" items="${empList}">
                      <tr>
                          <td>${emp.id}</td>
                          <td>${emp.tno}</td>
                          <td>${emp.name}</td>
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
                      </tr>
                  </c:forEach>
              </table>
            </div>
            <div class="col-md-12">
                <ul class="pager">
                    <li><a href="">上一页</a></li>
                    <li><a href="">1</a></li>
                    <li><a href="">2</a></li>
                    <li><a href="">3</a></li>
                    <li><a href="">4</a></li>
                    <li><a href="">下一页</a></li>
                </ul>
            </div>
          </c:if>
      </div>

      <%--编辑的模态框--%>
      <div class="modal fade" tabindex="-1" id="editModal">
          <div class="modal-dialog" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
                                  <label for="ename" class="col-sm-3 control-label">生日</label>
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
                                        <input type="text" class="form-control" id="managerId" name="managerId" required pattern="([1-9]\d*)|(BOSS)" title="格式错误">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="deptId" class="col-sm-3 control-label">部门代号</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="deptId" name="deptId" required pattern="([1-9]\d*)|(\u8f6e\u5c97)" title="格式错误">
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

  </body>
</html>
