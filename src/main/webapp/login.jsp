<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
</head>
<body>
    <h1>Login</h1>
    <form action="j_security_check" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="j_username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="j_password" required>
        </div>
        <button type="submit">Login</button>
    </form>
</body>
</html>