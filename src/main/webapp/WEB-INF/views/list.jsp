<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">
            <form action="" method="get" class="form-inline">
                <input type="text" placeholder="商品名称" value="${param.q_productName_like_s}" name="q_productName_like_s" class="form-control">
                <input type="text" placeholder="价格" value="${param.q_price_eq_bd}" name="q_price_eq_bd">
                <input type="text" placeholder="市场价格" value="${param.q_marketPrice_eq_bd}" name="q_marketPrice_eq_bd">
                <button class="btn btn-default">搜索</button>
            </form>
        </div>
    </div>
    <a href="/product/add" class="btn btn-success">添加</a>
    <table class="table">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>价格</th>
            <th>市场价</th>
            <th>产地</th>
            <th>评论数量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td><a href="/product/message/${product.id}">${product.productName}</a></td>
                <td>${product.price}</td>
                <td>${product.marketPrice}</td>
                <td>${product.place}</td>
                <td>${product.commentNum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>