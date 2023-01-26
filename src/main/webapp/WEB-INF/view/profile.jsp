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


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Profile</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>
<h4>
    <fmt:message key="label.name"/>  <c:out value="${requestScope.name}"/><br>
</h4>



<hr>
<form action="edit_profile">
    <input class="button" type="submit" value="<fmt:message key="button.edit"/>">
</form>


<c:if test="${sessionScope.role == 'student'}">
    <table class="sortable">
        <thead>
        <tr>
            <th><fmt:message key="table.head.test.name"/></th>
            <th><fmt:message key="table.head.subject"/></th>
            <th><fmt:message key="table.head.difficult"/></th>
            <th><fmt:message key="table.head.duration"/></th>
            <th><fmt:message key="table.head.grade"/></th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>


</body>
</html>
