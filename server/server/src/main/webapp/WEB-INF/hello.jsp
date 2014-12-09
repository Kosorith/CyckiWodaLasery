<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="css/bootstrap.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Admins panel</title>
    </head>
    <body>
        <form action="panel/challenges">
            <input type="submit" value="Challanges" class="btn btn-default" /> 
        </form>
        <form action="panel/users">
            <input type="submit" value="Users" class="btn btn-default" /> 
        </form>
        
    </body>
</html>
