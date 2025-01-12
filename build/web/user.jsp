<%-- 
    Document   : user
    Created on : Oct 18, 2024, 8:05:19 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Exhibition" %>
<%@ page import="model.Painting" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>Trang user</h1>

        <form  action="userTask" method="post">
            List Of Exhibition 
           
            <select name="exhibitionid" onchange="this.form.submit()">
                <option value = "all" ${exhibitionid eq -1 ? 'selected' :''}>All Exhibition</option>

                <c:forEach var="i" items="${requestScope.exhibition}">

                    <option value="${i.exhibitionId}" ${exhibitionid eq i.exhibitionId ? 'selected' :''} >${i.name}</option>

                </c:forEach>

            </select>


            <br>

            <br>

        </form>
        <br>

        <h2>Exhibition List</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Exhibition Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="i" items="${requestScope.exhibition}">
                    <tr>
                        <td><c:out value="${i.name}"/></td>
                        <td><c:out value="${i.startDate}"/></td>
                        <td><c:out value="${i.endDate}"/></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>

        <table border="1">
            <thead>
                <tr>
                    <th>PaintingImage</th>
                    <th>Title</th>
                    <th>Artist</th>
                    <th>YearCreated</th>
                    <th>Description</th>
                    <th>ExhibitionName</th>


                </tr>
            </thead>
            <tbody>


                <c:if test="${requestScope.painting ne null}">

                    <c:forEach items="${requestScope.painting}" var="h">

                        <tr>
                            <td>
                                <img src="${h.imagePath}" alt="${h.title}" style="width:400px; height:auto;" />
                            </td>
                            <td ><c:out value="${h.title}"></c:out></td>
                            <td ><c:out value="${h.artist}"></c:out></td>
                            <td><c:out value="${h.yearCreated}"></c:out></td>
                            <td><c:out value="${h.description}"></c:out></td>
                                <td>
                                <c:forEach items="${h.exhibitions}" var="ex">
                                    <c:out value="${ex.name}"></c:out><br>

                                </c:forEach>

                            </td>








                        </tr>




                    </c:forEach>
                </c:if>
            </tbody>
        </table>


    </body>
</html>
