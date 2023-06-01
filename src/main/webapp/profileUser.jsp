<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 30/05/2023
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Profilo Utente</title>
</head>
<body>
<jsp:include page="header.jsp" />
<br><br>
<h1>I tuoi dati</h1> <br><br>

<p>Nome: ${sessionScope.user.firstName}</p> <br>
<p>Cognome: ${sessionScope.user.lastName}</p> <br>
<p>Data di nascita: ${sessionScope.user.birthday}</p> <br>
<p>Email: ${sessionScope.user.email}</p><br>
<p>Username: ${sessionScope.user.username}</p><br>
<p>Numero patente: ${sessionScope.user.nPatente}</p><br>


<a href="UserServlet?action=edit&id=${sessionScope.user.id}"><button type="button">Modifica i tuoi dati</button></a>
</body>
</html>
