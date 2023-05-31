<%@ page import="entity.User" %>
<%@ page import="hibernate.repository.UserDao" %>
<%@ page import="hibernate.repository.impl.UserDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 30/05/2023
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<%
    User user;
    UserDao userDao = new UserDaoImpl();
    if (request.getParameter("id") != null) {
        int id = Integer.parseInt(request.getParameter("id"));
        user = userDao.getById(id);
    } else {
        HttpSession httpSession = request.getSession();
        user = (User) httpSession.getAttribute("user");
    }
%>
<html>
<head>
    <title>Profilo Utente</title>
</head>
<body>
<jsp:include page="header.jsp" />
<br><br>

<c:if test="${request.getParameter('id') == null}">
    <h1>I tuoi dati</h1> <br><br>
</c:if>
<c:if test="${request.getParameter('id') != null}">
    <h1>Dati di ${user.firstName} ${user.lastName}</h1> <br><br>
</c:if>

<p>Nome: ${user.firstName}</p> <br>
<p>Cognome: ${user.lastName}</p> <br>
<p>Data di nascita: ${user.birthday}</p> <br>
<p>Email: ${user.email}</p><br>
<p>Username: ${user.username}</p><br>
<p>Numero patente: ${user.nPatente}</p><br>


<a href="UserServlet?action=edit&id=${user.id}"><button type="button">Modifica i tuoi dati</button></a>
</body>
</html>
