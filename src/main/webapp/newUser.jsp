<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 31/05/2023
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nuovo utente</title>
</head>
<body>

<h1> Inserisci i dati del nuovo utente</h1> <br><br>

<form action="UserServlet?action=new" method="post">
  <label for="firstName">First name: </label> <input id="firstName" type="text" name="firstName" placeholder="First Name" /><br><br>
  <label for="lastName">Last name: </label> <input id="lastName" type="text" name="lastName" placeholder="Last Name" /><br><br>
  <label for="username">Username: </label> <input id="username" type="text" name="username" placeholder="username" /><br><br>
  <label for="password">Password: </label> <input id="password" type="password" name="password" placeholder="Password" /><br><br>
  <label for="email">Email: </label> <input id="email" type="text" name="email" placeholder="Email" /><br><br>
  <label for="bd">Data di nascita: </label> <input id="bd" type="date" name="bd" placeholder="Birthday" /><br><br>
  <label for="nPatente">Codice patente: </label> <input id="nPatente" type="text" name="nPatente" placeholder="Numero Patente" /><br><br>
  <input type="submit" value="Salva">
</form>

</body>
</html>
