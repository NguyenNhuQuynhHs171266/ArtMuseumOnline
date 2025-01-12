<%-- 
    Document   : register
    Created on : Oct 18, 2024, 10:01:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>Register Page</h1>
        <h3 style="color: pink">${requestScope.ms}</h3>
        <form action="register" method="POST">
            enter username: <input type="text" name="username" required /><br>
            enter password: <input type="password" name="password" required /><br>
            enter confirmPassword:
            <input type="password"  name="confirmPassword" required><br><br>
            <input type="submit" value="REGISTER" />
            
        </form>
        <a href="login">Đã có tài khoản? Đăng nhập</a>
    </body>
</html>
