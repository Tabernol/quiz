<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 27.12.2022
  Time: 01:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>tests</title>
</head>
<body>
<form action="filter_tests">
    <select name="sub">
        <option value="all"><c:out value="all"/></option>
        <c:forEach var="sub" items="${sessionScope.subjects}">
            <option value="${sub}"><c:out value="${sub}"/></option>
        </c:forEach>
    </select>
    <select name="order">
        <c:forEach var="order" items="${sessionScope.orders}">
            <option value="${order.order}"><c:out value="${order.order}"/></option>
        </c:forEach>
    </select><br>
    <input class="button" type="submit" value="filter">
</form>

<table class="sortable">
    <thead>
    <tr>
        <th>Test name</th>
        <th>Subject</th>
        <th>Difficult</th>
        <th>Duration</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="test" items="${requestScope.tests}">
        <tr>
            <td><c:out value="${test.name}"/></td>
            <td><c:out value="${test.subject}"/></td>
            <td><c:out value="${test.difficult}"/></td>
            <td><c:out value="${test.duration}"/></td>

            <td>
                <form action="take_test">
                    <input type="hidden" name="test_id" value="${test.id}">
                    <input class="button" type="submit" value="take test">
                </form>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<hr>

</body>
</html>
