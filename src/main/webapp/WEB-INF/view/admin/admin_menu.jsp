<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 21.12.2022
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin</title>
</head>
<body>
Hello admin
<hr>
<a href="<c:url value='/users' />">Users</a>
<a href="<c:url value='/tests' />">Tests</a>





<%--<a href="<c:url value='/users' />">All Users</a>--%>
<br>
<%--<a href="<c:url value='/to_create_test' />">Create test</a>--%>


<%--<c:forEach var="user" items="${requestScope.users}">--%>
<%--    <ul>--%>
<%--        <li>name: <c:out value="${user.name}"/></li>--%>
<%--        <li>login: <c:out value="${user.login}"/></li>--%>
<%--        <li>password: <c:out value="${user.password}"/></li>--%>
<%--        <a href="<c:url value='/'/>">Edit</a>--%>
<%--        <a href="<c:url value='/'/>">Edit</a>--%>
<%--        <a href="<c:url value='/'/>">Edit</a>--%>
<%--    </ul>--%>
<%--    <button> block/unblock</button>--%>
<%--    <hr/>--%>
<%--</c:forEach>--%>
<br>
<a href="<c:url value='/home'/>">Home</a>
<br>
<a href="<c:url value='/logout' />">Logout</a>
</body>
</html>
