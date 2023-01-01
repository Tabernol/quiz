<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 27.12.2022
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Profile</title>
</head>
<body>
name    <c:out value="${requestScope.name}"/><br>
login    <c:out value="${requestScope.login}"/><br>
password     <c:out value="${requestScope.password}"/><br>

<hr>
Add edit profile

<form action="home">
    <input class="button" type="submit" value="Home">
</form>


<table class="sortable">
    <thead>
    <tr>
        <th>Test name</th>
        <th>Subject</th>
        <th>Difficult</th>
        <th>Duration</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="result" items="${requestScope.user_result}">
        <tr>
            <td><c:out value="${result.testName}"/></td>
            <td><c:out value="${result.subject}"/></td>
            <td><c:out value="${result.difficult}"/></td>
            <td><c:out value="${result.duration}"/></td>
            <td><c:out value="${result.grade}"/></td>

<%--       take test again --%>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
