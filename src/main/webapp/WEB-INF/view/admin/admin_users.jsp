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
<table class="sortable">
    <thead>
    <tr>
        <th>User name</th>
        <th>Login</th>
        <th>Password</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>

            <td>
                <a href="<c:url value='/edit_user'>
                <c:param name="user_id" value="${user.id}"/></c:url>"/>edit
            </td>
            <td>
                <a href="<c:url value='/delete_user'>
                <c:param name="user_id" value="${user.id}"/></c:url>"/>delete
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
