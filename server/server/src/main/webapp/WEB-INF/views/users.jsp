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
        <form action="..">
            <input type="submit" value="Back to home" class="btn btn-default" /> 
        </form>
        <div style="margin-top:10%;width:70%;margin-left:auto;margin-right:auto;">
            <div class="panel panel-default">
                <div class="panel-heading">List of users</div>
                <div class="panel-body">
                    <p> costam </p>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>actions</th>
                            <th>login</th>
                            <th>password</th>
                            <th>nick</th>
                            <th>email</th>
                            <th>ranking</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="elem">
                            <tr>
                                <td>${elem.id}</td>
                                <td>
                                    <form action="users/delete">
                                        <input type="submit" value="Delete" class="btn btn-default" /> 
                                        <input type="hidden" name="login" value="${elem.login}" />
                                        <input type="hidden" name="password" value="${elem.password}" />
                                    </form>
                                </td>
                                <td>${elem.login}</td>
                                <td>${elem.password}</td>
                                <td>${elem.nick}</td>
                                <td>${elem.email}</td>
                                <td>${elem.ranking}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
