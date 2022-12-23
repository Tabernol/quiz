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
<a href="<c:url value='/home'/>">Home</a>
<br>
<a href="<c:url value='/logout' />">Logout</a>
<br>
<a href="<c:url value='/profile' />">profile</a>
<br>




<br>
<a href="<c:url value='/tests' />">All tests</a>
<a href="<c:url value='/testsAZ' />">Sort tests</a>

<%--<table>--%>
<%--<tr>--%>
<%--    <th>Test name</th>--%>
<%--    <th>Difficult</th>--%>
<%--    <th>Duration</th>--%>
<%--</tr>--%>
<%--<c:forEach var="test" items="${sessionScope.tests}">--%>
<%--        <tr> <c:out value="${test.name}"/></tr>--%>
<%--        <tr> <c:out value="${test.difficult}"/></tr>--%>
<%--        <tr> <c:out value="${test.duration}"/></tr>--%>
<%--    </table>--%>
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

<%--<c:forEach var="test" items="${sessionScope.tests}">--%>
<%--    <ul>--%>
<%--        <li>name: <c:out value="${test.name}"/></li>--%>
<%--        <li>difficult: <c:out value="${test.difficult}"/></li>--%>
<%--        <li>duration: <c:out value="${test.duration}"/></li>--%>
<%--    </ul>--%>
<%--    <hr/>--%>
<%--</c:forEach>--%>


</body>
</html>
