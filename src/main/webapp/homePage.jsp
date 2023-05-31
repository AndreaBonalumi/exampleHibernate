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
      <a href="BookingServlet?action=new">Nuova prenotazione</a> <br>

      <table>
          <tr>
              <th>ID</th>
              <th>Marca</th>
              <th>Modello</th>
              <th>Colore</th>
              <th>Descrizione</th>
              <th>Link</th>
              <th>Azioni</th>
          </tr>
          <c:forEach var="macchina" items="${bookings}">
          <tr>
              <td>${macchina.id}</td>
              <td>${macchina.brand}</td>
              <td>${macchina.model}</td>
              <td>${macchina.color}</td>
              <td>${macchina.description}</td>
              <td>${macchina.link}</td>
              <td>

              </td>

          </tr>
          </c:forEach>
      </table>
  </c:if>



  <c:if test="${sessionScope.user.admin}">
      <h4>Lista utenti:</h4> <br><br>
      <a href="UserServlet?action=new">Nuovo Utente</a> <br>

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
          <c:forEach var="tempUser" items="${users}">
              <tr>
                  <td>${tempUser.id}</td>
                  <td>${tempUser.username}</td>
                  <td>${tempUser.email}</td>
                  <td>${tempUser.created}</td>
                  <td>${tempUser.nPatente}</td>
                  <td>${tempUser.admin}</td>
                  <td>
                      <a href=""><button type="button">Modifica</button> </a>
                      <a href=""><button type="button">Cancella</button> </a>
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