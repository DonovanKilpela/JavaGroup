<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, music.business.Product, music.data.ProductDB" %>
<%
    // Fetch the list of products using ProductDB
    List<Product> productList = ProductDB.getProducts();
    request.setAttribute("productList", productList);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Products</title>
        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        <h1>Products Page</h1>
        <h2>Products</h2>
        <table>
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <td>${product.code}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>
                            <a href="editProduct.jsp?productCode=${product.code}">Edit</a> |
                            <a href="deleteConfirmation.jsp?productCode=${product.code}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Add Product Form -->
        <div style="width: 80%; margin: 20px auto;">
            <h2>Add Product</h2>
            <form action="productMaint" method="post">
                <input type="hidden" name="action" value="addProduct">
                <div>
                    <label for="code">Code:</label>
                    <input type="text" id="code" name="code">
                </div>
                <div>
                    <label for="description">Description:</label>
                    <input type="text" id="description" name="description">
                </div>
                <div>
                    <label for="price">Price:</label>
                    <input type="text" id="price" name="price">
                </div>
                <div>
                    <button type="submit">Add Product</button>
                    <a href="products.jsp">View Products</a>
                </div>
            </form>
        </div>

    </body>
</html>
