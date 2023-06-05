<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 31/05/2023
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nuova prenotazione</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>Prenotazione</h1>

<form action="BookingServlet?action=new" method="post">
    <label for="start">Data di inizio della prenotazione </label><input type="date" name="start" value="${param.start}" max="${param.end}" /> <br><br>
    <label for="end">Data di fine della prenotazione </label><input type="date" name="end" value="${param.end}" min="${param.start}" /><br><br>
    <input type="submit" value="cerca auto disponibili">
</form><br><br>
<form action="BookingServlet?action=book" method="post">
    <input hidden="true" id="start" type="date" name="start" value="${param.start}"/>
    <input hidden="true" id="end" type="date" name="end" value="${param.end}" />
    <c:if test="${carsDate != null}">
        <table>
            <thead>
            <tr>
                <th></th>
                <th>Marca</th>
                <th>Modello</th>
                <th>Colore</th>
                <th>Descrizione</th>
                <th>Link</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="car" items="${carsDate}">
                    <tr>
                        <td>
                            <input type="radio" name="carSelected" value="${car.id}">
                        </td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.color}</td>
                        <td>${car.description}</td>
                        <td>${car.link}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="submit" value="Prenota">
    </c:if>
</form>
</body>
</html>





<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    table th, table td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    table th {
        background-color: #f2f2f2;
    }

    table tr:hover {
        background-color: #f5f5f5;
    }

    table td {
        font-size: 14px;
    }

</style>