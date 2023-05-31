<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 29/05/2023
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav>
  <div class="topnav">

    <a href="UserServlet?action=home">Home</a>
    <c:if test="${sessionScope.user.admin}">
      <a href="CarServlet?action=all">Parco auto</a>
    </c:if>
    <a href="profileUser.jsp">Profilo utente</a>

  </div>
</nav>
</body>
</html>



<style>
  .topnav {
    background-color: #333;
    overflow: hidden;
  }

  .topnav a {
    float: left;
    color: #f2f2f2;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    font-size: 17px;
  }

  .topnav a:hover {
    background-color: #ddd;
    color: black;
  }
</style>