<%-- 
    Document   : info
    Created on : Oct 18, 2024, 8:06:29 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>Profile page</h1>
        <h3>
            Username :${sessionScope.account.username}<br>
            <c:if test="${sessionScope.account.role==1}">
                <br>
                CHAO MUNG BAN DEN VOI KENH ADMIN
                
                <br><br>
                
                
                <img src="image/meme2.jpg" width="600" height="600" alt="meme2"/>



               
               

                



            </c:if>

            <c:if test="${sessionScope.account.role==2}" >
                <br>
                
                CHAO MUNG BAN USER! 
                
                <br><br>
                
                <img src="image/meme1.jpg" width="400" height="500" alt="meme1"/>



            </c:if>


        </h3>
    </body>
</html>
