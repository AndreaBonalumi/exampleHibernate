<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 29/05/2023
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>inserisci macchina</title>
</head>
<body>
<jsp:include page="header.jsp" />
<br>
<h1>Inserisci i dati della macchina</h1>

<form action="CarServlet?action=insert" method="post">

    <label for="brand">Marca*: </label><input id="brand" type="text" name="brand" placeholder="Lamborghini" required><br><br>
    <label for="model">Modello*: </label><input id="model" type="text" name="model" placeholder="Huracan" required><br><br>
    <label for="color">Colore*: </label><input id="color" type="text" name="color" placeholder="Blue and orange" required><br><br>
    <label for="description">Descrizione: </label><input id="description" type="text" name="description" placeholder="Es. cilindrata, cavalli ecc."><br><br>
    <label for="link">Link foto: </label><input id="link" type="text" name="link" placeholder="Link"><br><br>
    <label for="year">Anno*: </label><input id="year" type="text" name="year" placeholder="Year" required><br><br>

    <input type="submit" value="Inserisci"><br>
</form>
</body>
</html>
