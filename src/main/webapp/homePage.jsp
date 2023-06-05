<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 29/05/2023
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>


  <jsp:include page="header.jsp" />
  <br/>
  <h1>Benvenuto <c:out value="${sessionScope.user.firstName}" /></h1>

  <br><br>
  <c:if test="${!sessionScope.user.admin}">
      <h4>Le tue prenotazioni:</h4> <br><br>
      <a href="newBooking.jsp">Nuova prenotazione</a> <br>

      <table>
          <tr>
              <th>ID</th>
              <th>Data Inizio</th>
              <th>Data Fine</th>
              <th>Marca auto</th>
              <th>Modello auto</th>
              <th>Stato</th>
              <th>Azioni possibili</th>
          </tr>
          <c:if test="${bookings != null}">
              <c:forEach var="booking" items="${bookings}">
                  <tr>
                      <td>${booking.id}</td>
                      <td>${booking.dateBookingStart}</td>
                      <td>${booking.dateBookingEnd}</td>
                      <td>${booking.car.brand}</td>
                      <td>${booking.car.model}</td>
                      <td>${booking.status}</td>
                      <td>
                          <c:if test="${booking.status > -1}">

                              <a href="BookingServlet?action=edit&id=${booking.id}"><button type="button">Modifica</button></a>
                              <a href="BookingServlet?action=delete&id=${booking.id}"><button type="button" onclick="window.alert('prenotazione cancellalta')">Cancella</button></a>

                          </c:if>
                      </td>
                  </tr>
              </c:forEach>
          </c:if>
      </table>
  </c:if>



  <c:if test="${sessionScope.user.admin}">
      <h4>Lista utenti:</h4> <br><br>
      <form action="UserServlet?action=filter" method="post">
          <label for="filter">Filtra: </label>
          <select name="fieldSearch">
              <option name="firstName" value="firstName">First name</option>
              <option name="lastName" value="lastName">Last name</option>
              <option name="username" value="username">username</option>
              <option name="email" value="email">Email</option>
          </select>
          <input id="filter" type="text" placeholder="cerca.." name="filter" />
          <input type="submit" value="cerca" />
      </form>
      <a href="newUser.jsp">Nuovo Utente</a> <br>

      <table>
          <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Email</th>
              <th>Creato</th>
              <th>Numero Patente</th>
              <th>Admin</th>
              <th>Azioni</th>
          </tr>
          <c:forEach var="tempUser" items="${userList}">
              <tr>
                  <td><a href="UserServlet?action=user&id=${tempUser.id}">${tempUser.id}</a></td>
                  <td>${tempUser.username}</td>
                  <td>${tempUser.email}</td>
                  <td>${tempUser.created}</td>
                  <td>${tempUser.nPatente}</td>
                  <td>${tempUser.admin}</td>
                  <td>
                      <a href="UserServlet?action=edit&id=${tempUser.id}"><button type="button">Modifica</button></a>
                      <a href="UserServlet?action=delete&id=${tempUser.id}"><button type="button" onclick="window.alert('Utente cancellato')">Cancella</button> </a>
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