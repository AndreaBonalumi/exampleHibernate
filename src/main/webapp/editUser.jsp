<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 01/06/2023
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica</title>
</head>
<body>

<jsp:include page="header.jsp" />
<h1>Modifica dati:</h1>

<form action="UserServlet?action=edit?id=${param.userProfile.id}" method="post">
  <label for="firstName">First name: </label><input id="firstName" type="text" name="firstName" value="${param.userProfile.firstName}" /><br><br>
  <label for="lastName">Last Name: </label><input id="lastName" type="text" name="lastName" value="${param.userProfile.lastName}" /><br><br>
  <label for="password">New password: </label><input id="password" type="password" name="password" placeholder="password" /><br><br>
  <label for="nPatente">Codice patente: </label><input id="nPatente" type="text" name="nPatente" value="${param.userProfile.nPatente}" /><br><br>
  <input type="submit" value="Salva" />
</form>

</body>
</html>
