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
                <div class="panel-heading">Challenges</div>
                <div class="panel-body">
                    <p> Lista wszytkich wyzwań w systemie</p>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Actions</th>
                            <th>Name</th>
                            <th>Password</th>
                            <th>Coords</th>
                            <th>Secreet Code</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Points</th>
                            <th><center>Hints</center></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${challanges}" var="elem">
                            <tr>
                                <td>${elem.id}</td>
                                <td>
                                    <form action="challenges/delete">
                                        <input type="submit" value="Delete" class="btn btn-default" /> 
                                        <input type="hidden" name="id" value="${elem.id}" />
                                    </form>
                                    <c:if test="${elem.status == false}">
                                        <form action="challenges/veryfi">
                                            <input type="submit" value="Veryfi" class="btn btn-default" /> 
                                            <input type="hidden" name="id" value="${elem.id}" />
                                            <input type="hidden" name="points" value="${elem.points}" />
                                            <input type="hidden" name="status" value="true" />
                                        </form>
                                    </c:if>
                                </td>
                                <td>${elem.name}</td>
                                <td>${elem.password}</td>
                                <td>${elem.location}</td>
                                <td>${elem.secretPassword}</td>
                                <td>${elem.description}</td>
                                <td>${elem.status}</td>
                                <td>${elem.points}</td>
                                <td>
                                    <ul>
                                         <c:forEach items="${elem.hints}" var="hint">
                                            <li>${hint.id} : ${hint.text} : ${hint.distance} <img alt="A" src="/server/resource/img/img.jpg" width="320" height="240"></li>
                                        </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
