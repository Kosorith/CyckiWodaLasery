<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Admins panel</title>
    </head>
    <body>
        <center>
            <form action="../panel/login" method="post">
                <p style="color:red">${error}<p>
                <label for="name">Login:</label>
                <input id="name" name="login" type="text" ><br>
                <label for="pass">Password:</label>
                <input id="pass" name="pass" type="password" ><br>
                <input type="submit" value="LOGIN">
            </form>
        </center>
        
    </body>
</html>
