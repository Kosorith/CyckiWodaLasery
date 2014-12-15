<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admins panel</title>
    </head>
    <body>
        <form action="..">
            <input type="submit" value="Back to home" class="btn btn-default" /> 
        </form>
        <div style="margin-top:10%;width:70%;margin-left:auto;margin-right:auto;">
            <div class="panel panel-default">
                <div class="panel-heading">Ranking</div>
                <div class="panel-body">
                    <p> Strona pokazująca ranking wszytkich użytkowników</p>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>User (nick)</th>
                            <th>Points</th>
                            <th>Number of completed challenges</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ranking}" var="elem">
                            <tr>
                                <td>${elem.id}</td>
                                <td>${elem.user.nick}</td>
                                <td>${elem.points}</td>
                                <td>${elem.completedChallengesNum}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
