<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 27.12.2022
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
name    <c:out value="${requestScope.name}"/><br>
login    <c:out value="${requestScope.login}"/><br>
password     <c:out value="${requestScope.password}"/><br>



</body>
</html>
