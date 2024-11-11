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
    <title>Delete Confirmation</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
    <h1>Delete Product</h1>
    <p>Are you sure you want to delete the following product?</p>
    <p><strong>Code:</strong> ${product.code}</p>
    <p><strong>Description:</strong> ${product.description}</p>
    <p><strong>Price:</strong> ${product.priceCurrencyFormat}</p>
    <form action="productMaint" method="post">
        <input type="hidden" name="action" value="deleteProduct">
        <input type="hidden" name="code" value="${product.code}">
        <button type="submit">Delete</button>
        <a href="products.jsp">View Products</a>
    </form>
</body>
</html>
