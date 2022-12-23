<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>tests</title>
</head>
<body>

<table class="sortable">
    <thead>
    <tr>
        <th>Test name</th>
        <th>Difficult</th>
        <th>Duration</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="test" items="${requestScope.tests}">
        <tr>
            <td><c:out value="${test.name}"/></td>
            <td><c:out value="${test.difficult}"/></td>
            <td><c:out value="${test.duration}"/></td>
            <td><c:out value="${test.subjectName}"/></td>
            <td><button>Take test</button> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
