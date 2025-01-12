<%-- 
    Document   : insert
    Created on : Oct 24, 2024, 9:53:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Painting" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Exhibition" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
        <h1>Insert new Painting</h1>
        <form action="insert" method="POST" >
            <label>PaintingImage:</label><br>

            <input type="text" name="imagePath"  required><br>


            <label>Title:</label><br>
            <input type="text" name="title"  required><br>
            <label>Artist:</label><br>
            <input type="text" name="artist"  required><br>
            <label>YearCreated:</label><br>
            <input type="number" name="yearCreated"  required><br>

            <label>Description:</label><br>
            
            <textarea name="description" rows="5" cols="50" required></textarea>
            
            
            
            <br>

            <label>Exhibition Name</label><br>

            <c:forEach var="i" items="${exhibition}">
                <label>
                    <input type="checkbox" name="exhibitionId[]" value="${i.exhibitionId}">
                    ${i.name}
                </label><br/>
            </c:forEach>
            <br>
            <input type="submit" value="INSERT" />
        </form>
            <br>
            <button onclick="window.location.href = 'quynh'">BACK</button>
    </body>
</html>
