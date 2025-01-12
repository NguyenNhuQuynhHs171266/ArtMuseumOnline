<%-- 
    Document   : insertEx
    Created on : Oct 25, 2024, 8:50:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>InsertEx Page</title>
    </head>
    <body>
        <h2>Insert new Exhibition</h2>
        <br>
    <form action="insertEx" method="post">
        <label for="name">ExhibitionName:</label><br>
        <input type="text"  name="name" required><br><br>

        <label for="startDate">Start-Date:</label><br>
        <input type="date"  name="startDate" required><br><br>

        <label for="endDate">End-Date:</label><br>
        <input type="date"  name="endDate" required><br><br>

        <button type="submit">CONFIRM</button>
    </form>
        
        <br>
        
        <button onclick="window.location.href = 'quynh'">BACK</button>
    </body>
</html>
