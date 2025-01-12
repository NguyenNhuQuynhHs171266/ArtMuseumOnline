<%-- 
    Document   : login
    Created on : Oct 17, 2024, 11:14:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>Login Page</h1>
        <h3 style="color: pink">${requestScope.ms}</h3>
        <form action="login" method="POST">
            enter username: <input type="text" name="user"  /><br>
            enter password: <input type="password" name="pass"  /><br>
            <input type="submit" value="LOGIN" />
            
        </form>
        
        Don't have an account ? 
        <a href="register">Register</a>
    </body>
</html>
