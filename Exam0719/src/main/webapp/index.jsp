<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-3.5.1.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="container" style="margin-top: 60px">
    <div class="row" style="margin-bottom: 60px">
        <div class="container">
            <button class="btn btn-success" data-toggle="modal" data-target="#addModal">
                <span class="glyphicon glyphicon-plus"></span>添加商品
            </button>
            <button class="btn btn-primary" onclick="searchById()">
                <span class="glyphicon glyphicon-search"></span>通过id查询商品
            </button>
            <button class="btn btn-primary" onclick="window.location.href='showAll'">
                <span class="glyphicon glyphicon-leaf"></span>展示所有商品
            </button>
            <button class="btn btn-primary" onclick=" getFirstPage();">
                <span class="glyphicon glyphicon-leaf"></span>分页
            </button>
            <button class="btn btn-primary" onclick=" pageSearchFailed();">
                <span class="glyphicon glyphicon-leaf"></span>分页
            </button>

        </div>
    </div>
    <!--商品-->
    <c:if test="${!empty productList}">
        <div class="container">
            <div class="row" style="min-height: 600px">
                <c:forEach var="p" items="${productList}">
                    <div class="col-xs-6 col-sm-4 col-md-3">
                        <div class="thumbnail pro-item">
                            <img src="imgs/${p.image}" alt="">
                            <div class="caption">
                                <h4 class="title">${p.pname}</h4>
                                <p class="info">${p.pinfo} </p>
                                <p class="price">${p.price}元起</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!--分页导航条-->
            <div class="row">
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


    <%-- 分页--%>

    <div class="container">
        <c:if test="${!empty productPageList}">
            <div class="row" style="min-height: 600px">
                <c:forEach var="p" items="${productPageList}">
                    <div class="col-xs-6 col-sm-4 col-md-3">
                        <div class="thumbnail pro-item">
                            <img src="imgs/${p.image}" alt="">
                            <div class="caption">
                                <h4 class="title">${p.pname}</h4>
                                <p class="info">${p.pinfo} </p>
                                <p class="price">${p.price}元起</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <ul class="pager">
                    <li><a href="javascript:;" onclick=" getPagePath(this)">&laquo;</a></li>
                    <li><a href="javascript:;" onclick=" getPagePath(this)">1</a></li>
                    <li><a href="javascript:;" onclick=" getPagePath(this)">2</a></li>
                    <li><a href="javascript:;" onclick=" getPagePath(this)">3</a></li>
                    <li><a href="javascript:;" onclick=" getPagePath(this)">4</a></li>
                    <li><a href="javascript:;" onclick=" getPagePath(this)">&raquo;</a></li>
                </ul>
            </div>
        </c:if>
    </div>
    <%--<script>
        <%
            String pageFlag="1";
            pageFlag=(String) request.getAttribute("productPageFlag");
            out.print(pageFlag);
            if ("2".equals(pageFlag)){
                out.print("pageUpFlagFailed();");
                out.print("pageSearchFailed();");
            }
        %>
    </script>--%>
        <%
            String pageFlag="1";
            pageFlag=(String) request.getAttribute("productPageFlag");
            if ("2".equals(pageFlag)){
                out.print("<h2>最后一页了</h2>");
            }
        %>
    <script>
        getUserPage(${productPage});
    </script>


    <%--    商品添加模态框--%>
    <div class="modal fade" id="addModal" tabindex="-1">
        <div class="modal-dialog" style="width: 500px;margin-top: 150px">
            <div class="modal-content">
                <div class="modal-header">
                    <a href="#" class="close" data-dismiss="modal">&times;</a>
                    <h4 class="modal-title text-left">商品添加</h4>
                </div>
                <div class="modal-body">
                    <form action="add" class="form-horizontal" method="get" id="addItem">
                        <div class="form-group">
                            <label for="pname" class="control-label col-md-3 ">商品名:</label>
                            <div class="col-md-9">
                                <input type="text" id="pname" name="pname" class="form-control"
                                       placeholder="商品名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pinfo" class="control-label col-md-3">商品介绍:</label>
                            <div class="col-md-9">
                                <input type="text" id="pinfo" name="pinfo" class="form-control" placeholder="商品介绍">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">商品图片:</label>
                            <div class="col-md-5">
                                <select name="pimage" class="form-control">
                                    <option value="p1.webp">p1.webp</option>
                                    <option value="p2.webp">p2.webp</option>
                                    <option value="p3.webp">p3.webp</option>
                                    <option value="p4.webp">p4.webp</option>
                                    <option value="p5.webp">p5.webp</option>
                                    <option value="p6.webp">p6.webp</option>
                                    <option value="p7.webp">p7.webp</option>
                                    <option value="p8.webp">p8.webp</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="price" class="control-label col-md-3 ">商品价格:</label>
                            <div class="col-md-9">
                                <input type="text" id="price" name="price" class="form-control"
                                       placeholder="商品价格">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="stock" class="control-label col-md-3">商品库存:</label>
                            <div class="col-md-9">
                                <input type="text" id="stock" name="stock" class="form-control" placeholder="商品库存">
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="col-md-6 col-md-offset-3 ">
                                <button type="button" class="btn btn-primary" onclick="addItemBtn()">添加商品</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%--查询成功模态框--%>
    <div class="modal fade" id="searchSuccessModal" tabindex="-1" data-backdrop='static'>
        <div class="modal-dialog" style="width: 1000px;margin-top: 150px">
            <div class="modal-content">
                <div class="modal-header">
                    <a href="#" class="close" data-dismiss="modal">&times;</a>
                    <h4 class="modal-title text-left">查询结果</h4>
                </div>
                <div class="modal-body text-center">
                    <table class="table table-bordered text-center">
                        <tbody>
                        <tr>
                            <td>编号</td>
                            <td>商品名称</td>
                            <td>商品图片</td>
                            <td>商品价格</td>
                            <td>商品库存</td>
                            <td>商品销量</td>
                            <td>其他操作</td>
                        </tr>
                        <tr style="line-height: 57px">
                            <td>${product.id}</td>
                            <td>${product.pname}</td>
                            <td><img src="imgs/${product.image}" alt="" style="width: 60px"></td>
                            <td>${product.price}元</td>
                            <td>${product.stock}</td>
                            <td>${product.sale}</td>
                            <td>
                                <button class="btn btn-danger" onclick="delTr(this)">&times;删除</button>
                            </td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </div>
    <%--    查询失败模态框--%>
    <div class="modal fade" id="searchFailedModal" tabindex="-1">
        <div class="modal-dialog" style="width: 400px;margin-top: 150px">
            <div class="modal-content">
                <div class="modal-body text-center">
                    <h4>没有此id商品</h4>
                </div>
            </div>
        </div>
    </div>
    <%--    添加模态框--%>
    <div class="modal fade" id="addResModal" tabindex="-1">
        <div class="modal-dialog" style="width: 400px;margin-top: 150px">
            <div class="modal-content">
                <div class="modal-body text-center">
                    <h4>${addResStr}</h4>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="pageEmptyModal" tabindex="-1">
        <div class="modal-dialog" style="width: 400px;margin-top: 150px">
            <div class="modal-content">
                <div class="modal-body text-center">
                    <h4>最后一页了</h4>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="pageOneModal" tabindex="-1">
        <div class="modal-dialog" style="width: 400px;margin-top: 150px">
            <div class="modal-content">
                <div class="modal-body text-center">
                    <h4>到头了</h4>
                </div>
            </div>
        </div>
    </div>


    <c:if test="${!empty addResStr}">
        <script>
            showAddModal();
        </script>
    </c:if>
    <c:if test="${!empty product}">
        <script>
            searchSuccess();
        </script>
    </c:if>
    <script>
        <%
            String flag = "1";
            flag = (String) request.getAttribute("flag");
            if ("2".equals(flag)) {
                out.write("searchFailed()");
            }
        %>
    </script>
</div>
</body>
</html>
