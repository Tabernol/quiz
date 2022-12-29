<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>quiz</title>
</head>
<body>
<form method="post" action="create_test">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="text" required placeholder="name" name="name" value="${requestScope.name}"><br>
    <input type="text" required placeholder="subject" name="subject" value="${requestScope.subject}"><br>
    <input type="number" required placeholder="difficult" name="difficult" value="${requestScope.difficult}"><br>
    <input type="number" required placeholder="duration" name="duration" value="${requestScope.duration}"><br>
    <input class="button" type="submit" value="Create test">
</form>
<c:out value="${requestScope.message}"/>

</body>
</html>
