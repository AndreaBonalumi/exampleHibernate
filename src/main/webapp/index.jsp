<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>

    <form action="UserServlet?action=home" method="post">
        <label for="username">Username </label> <input id="username" type="text" name="username" /> <br><br>
        <label for="password">Password </label> <input id="password" type="password" name="password" /><br><br>
        <input type="submit" value="Entra">
    </form>
    </body>
</html>