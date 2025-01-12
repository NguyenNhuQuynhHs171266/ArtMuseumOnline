<%-- 
    Document   : edit
    Created on : Oct 23, 2024, 8:35:53 AM
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
        <title>Edit Painting</title>
    </head>
    <body>
        <h1>Edit Painting</h1>
        <form action="edit" method="POST" >
            <input type="hidden" name="id" value="${id}"><!-- comment -->
            <br>
            <label>PaintingImage:</label><br>

            <input type="text" name="imagePath" value="${painting.imagePath}" required><br>

            <br>
            <label>Title:</label><br>
            <input type="text" name="title" value="${painting.title}" required><br><br>
            <label>Artist:</label><br>
            <input type="text" name="artist" value="${painting.artist}" required><br><br>
            <label>YearCreated:</label><br>
            <input type="number" name="yearCreated" value="${painting.yearCreated}" required><br>
            <br>

            <label>Description:</label><br>
            

            
            <textarea  name="description" rows="5" cols="50" required>${painting.description}</textarea><br>

            <label>Exhibition Name</label><br>

            <c:forEach var="i" items="${exhibition}">
                <label>
                    <input type="checkbox" name="exhibitionId[]" value="${i.exhibitionId}">
                    ${i.name}
                </label><br/>
            </c:forEach>
            <br>
            <input type="submit" value="CONFIRM" />
        </form>
        <br>
        <button onclick="window.location.href = 'quynh'">BACK</button>
    </body>
    
    
</html>
