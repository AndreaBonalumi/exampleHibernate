<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 01/06/2023
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Prenotazioni</title>
</head>
<body>

<jsp:include page="header.jsp" />

<h1>Prenotazioni di ${userBooking.firstName}</h1>

<c:if test="${bookings != null}">
    <table>
        <tr>
            <th>ID</th>
            <th>Data inizio</th>
            <th>Data fine</th>
            <th>Marca</th>
            <th>Modello</th>
            <th>Stato</th>
            <th>Azioni</th>
        </tr>
        <c:forEach var="tempbooking" items="${bookings}">
            <tr>
                <td>${tempbooking.id}</td>
                <td>${tempbooking.dateBookingStart}</td>
                <td>${tempbooking.dateBookingEnd}</td>
                <td>${tempbooking.car.brand}</td>
                <td>${tempbooking.car.model}</td>
                <td>${tempbooking.status}</td>
                <td>
                    <a href="BookingServlet?action=approve&id=${tempbooking.id}"><button type="button" onclick="window.alert('prenotazione approvata')">Approva</button> </a>
                    <a href="BookingServlet?action=decline&id=${tempbooking.id}"><button type="button" onclick="window.alert('prenotazione rifiutata')">Rifiuta</button> </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
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