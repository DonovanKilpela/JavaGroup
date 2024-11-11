<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="music.business.Product, music.data.ProductDB" %>
<%
    // Retrieve product from ProductDB
    String productCode = request.getParameter("productCode");
    Product product = ProductDB.selectProduct(productCode);
    request.setAttribute("product", product);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
    <h1>Edit Product</h1>
    <form action="productMaint" method="post">
        <input type="hidden" name="action" value="updateProduct">
        <div>
            <label for="code">Code:</label>
            <input type="text" id="code" name="code" value="${product.code}" readonly>
        </div>
        <div>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="${product.description}">
        </div>
        <div>
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" value="${product.price}">
        </div>
        <div>
            <button type="submit">Update Product</button>
            <a href="products.jsp">View Products</a>
        </div>
    </form>
</body>
</html>
