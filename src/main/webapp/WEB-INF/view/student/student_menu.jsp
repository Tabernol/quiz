<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 21.12.2022
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student</title>
</head>
<body>
Hello student ${sessionScope.name}

<br>
<a href="<c:url value='/logout' />">Logout</a>
<br>
<a href="<c:url value='/profile' />">profile</a>
<br>
<a href="<c:url value='/filter_tests'>
  <c:param name="order" value="name asc" />
    <c:param name="sub" value="all"/>
    <c:param name="rows" value="5"/>
    <c:param name="page" value="1"/>
</c:url>">Tests</a>







</body>
</html>
