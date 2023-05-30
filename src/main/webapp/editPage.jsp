<%@ page import="entity.Car" %>
<%@ page import="hibernate.repository.CarDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 30/05/2023
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica</title>
</head>
<body>
<%
    Car car = (Car) request.getAttribute("car");
%>

<h1>Modifica ${car.brand} ${car.model}</h1><br>

<form action="EditServlet?id=${car.id}" method="post">

    <span>Marca: ${car.brand}</span><br><br>
    <span>Modello: ${car.model}</span><br><br>
    <span>Anno: ${car.year}</span><br><br>
    <label for="color">Colore: </label> <input id="color" type="text" name="color" placeholder="color" value="${car.color}" required><br><br>
    <label for="description">Descrizione: </label> <input id="description" type="text" name="description" placeholder="description" value="${car.description}"><br><br>
    <label for="link">Link foto: </label> <input id="link" type="text" name="link" placeholder="link" value="${car.link}"><br><br>
    <input type="submit" value="Salva">
</form>
</body>
</html>
