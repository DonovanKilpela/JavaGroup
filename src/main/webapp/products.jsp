<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/requiredFieldTag.tld" prefix="custom" %>
<%@ page import="java.util.*, music.business.Product, music.data.ProductDB" %>
<%
    // Get product list from ProductDB
    List<Product> productList = ProductDB.selectProducts();
    request.setAttribute("productList", productList);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
    <link rel="stylesheet" href="styles/main.css">
    <script>
        // JavaScript to check required fields before form submission
        function validateForm() {
            let code = document.getElementById("code").value.trim();
            let description = document.getElementById("description").value.trim();
            let price = document.getElementById("price").value.trim();
            let isValid = true;

            if (!code) {
                document.getElementById("codeError").innerText = "Fill out required field";
                isValid = false;
            } else {
                document.getElementById("codeError").innerText = "";
            }

            if (!description) {
                document.getElementById("descriptionError").innerText = "Fill out required field";
                isValid = false;
            } else {
                document.getElementById("descriptionError").innerText = "";
            }

            if (!price) {
                document.getElementById("priceError").innerText = "Fill out required field";
                isValid = false;
            } else {
                document.getElementById("priceError").innerText = "";
            }

            return isValid; // Prevent form submission if any field is invalid
        }
    </script>
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
                    <td>${product.priceCurrencyFormat}</td>
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
        <form action="productMaint" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="action" value="addProduct">
            
            <!-- Code Field with Required Field Custom Tag -->
            <div>
                <label for="code">Code:</label>
                <input type="text" id="code" name="code" value="${param.code}">
                <custom:requiredFieldTag fieldValue="${param.code}" />
                <span id="codeError" style="color: red;"></span>
            </div>
            
            <!-- Description Field with Required Field Custom Tag -->
            <div>
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="${param.description}">
                <custom:requiredFieldTag fieldValue="${param.description}" />
                <span id="descriptionError" style="color: red;"></span>
            </div>
            
            <!-- Price Field with Required Field Custom Tag -->
            <div>
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="${param.price}">
                <custom:requiredFieldTag fieldValue="${param.price}" />
                <span id="priceError" style="color: red;"></span>
            </div>
            
            <div>
                <button type="submit">Add Product</button>
                <a href="products.jsp">View Products</a>
            </div>
        </form>
    </div>

</body>
</html>