<%-- 
    Document   : seller
    Created on : Oct 18, 2024, 8:17:24 AM
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
        <title>List Page</title>
    </head>
    <body>
        <jsp:include page="../menu.jsp"/>
        <h1>Trang ban hang</h1>

        <h2>Painting List</h2>



        <form  action="quynh" method="post">
            List Of Exhibition 
            
            <select name="exhibitionid" onchange="this.form.submit()">
                <option value = "all" ${exhibitionid eq -1 ? 'selected' :''}>All Exhibition</option>

                <c:forEach var="i" items="${requestScope.exhibition}">

                    <option value="${i.exhibitionId}" ${exhibitionid eq i.exhibitionId ? 'selected' :''} >${i.name}</option>

                </c:forEach>

            </select>




        </form>
        <br>

        <button onclick="window.location.href = 'insertEx'">Insert New Exhibition</button>
        <br>
<br>
        <button onclick="window.location.href = 'insert'">Insert New Painting</button>


        <br>
        <h2>Exhibition List</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Exhibition Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="i" items="${requestScope.exhibition}">
                    <tr>
                        <td><c:out value="${i.name}"/></td>
                        <td><c:out value="${i.startDate}"/></td>
                        <td><c:out value="${i.endDate}"/></td>
                        <td>
                            <button onclick="if (confirm('Are you sure you want to delete this exhibition?'))
                                window.location.href = 'deleteEx?exhibitionId=${i.exhibitionId}'">Delete</button>
                        </td>
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

                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>


                <c:if test="${requestScope.painting ne null}">

                    <c:forEach items="${requestScope.painting}" var="h">

                        <tr>
                            <td>
                                <img src="${h.imagePath}" alt="${h.title}" style="width:200px; height:auto;" />
                            </td>
                            <td><c:out value="${h.title}"></c:out></td>
                            <td><c:out value="${h.artist}"></c:out></td>
                            <td><c:out value="${h.yearCreated}"></c:out></td>
                            <td><c:out value="${h.description}"></c:out></td>
                                <td>
                                <c:forEach items="${h.exhibitions}" var="ex">
                                    <c:out value="${ex.name}"></c:out><br>

                                </c:forEach>

                            </td>





                            <td>
                                <button onclick="window.location.href = 'edit?id=${h.paintingId}'">Edit</button>

                                <button onclick="if (confirm('Are you sure?'))
                                            window.location.href = 'delete?id=${h.paintingId}'">Delete</button>


                            </td>


                        </tr>




                    </c:forEach>
                </c:if>
            </tbody>
        </table>
        <br>



    </body>
</html>




