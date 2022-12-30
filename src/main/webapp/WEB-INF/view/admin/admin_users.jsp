<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>users</title>
</head>
<body>
<form action="home">
    <input class="button" type="submit" value="Home">
</form>
<table class="sortable">
    <thead>
    <tr>
        <th>User name</th>
        <th>Login</th>
        <th>Password</th>
        <th>Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.blocked}"/></td>

            <td>

                <form action="edit_user">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input class="button" type="submit" value="edit user">
                </form>
            </td>
            <td>
                <form action="delete_user">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input class="button" type="submit" value="delete user">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>



</body>
</html>
