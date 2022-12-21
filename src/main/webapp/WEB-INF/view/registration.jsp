<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 20.12.2022
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<div class="form">
    <form method="post" action="registration">
        <input type="text" required placeholder="name" name="name"><br>
        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br>
        <input class="button" type="submit" value="register">
    </form>
</div>

<br>
<a href="<c:url value='/home' />">back</a>
</body>
</html>
