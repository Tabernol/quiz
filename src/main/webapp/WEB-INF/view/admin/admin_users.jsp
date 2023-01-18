<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>users</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>
<table class="sortable">
    <thead>
    <tr>
        <th><fmt:message key="table.head.name"/></th>
        <th><fmt:message key="table.head.login"/></th>
        <th><fmt:message key="table.head.status"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.blocked}"/></td>


            <td>
                <form method="post" action="block">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input class="button" type="submit" value=" <fmt:message key="button.block"/>">
                </form>
            </td>
            <td>
                <form action="edit_user">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input class="button" type="submit" value=" <fmt:message key="button.edit.user"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
