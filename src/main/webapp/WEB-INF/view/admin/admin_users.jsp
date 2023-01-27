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
<%--<table class="sortable">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th><fmt:message key="table.head.name"/></th>--%>
<%--        <th><fmt:message key="table.head.login"/></th>--%>
<%--        <th><fmt:message key="table.head.status"/></th>--%>
<%--        <th></th>--%>
<%--        <th></th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>

<%--    <c:forEach var="user" items="${requestScope.users}">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${user.name}"/></td>--%>
<%--            <td><c:out value="${user.login}"/></td>--%>
<%--            <td><c:out value="${user.blocked}"/></td>--%>


<%--            <td>--%>
<%--                <form method="post" action="block">--%>
<%--                    <input type="hidden" name="user_id" value="${user.id}">--%>
<%--                    <input class="button" type="submit" value=" <fmt:message key="button.block"/>">--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <form action="edit_user">--%>
<%--                    <input type="hidden" name="user_id" value="${user.id}">--%>
<%--                    <input class="button" type="submit" value=" <fmt:message key="button.edit.user"/>">--%>
<%--                </form>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>


<table class="table align-middle mb-0 w-auto bg-white">
    <thead class="bg-secondary">
    <tr>
        <th><fmt:message key="table.head.name"/></th>
        <th><fmt:message key="table.head.login"/></th>
        <th><fmt:message key="table.head.status"/></th>
        <th>Position</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${requestScope.users}">
    <tr>
        <td>
            <div class="d-flex align-items-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-bounding-box" viewBox="0 0 16 16">
                    <path d="M1.5 1a.5.5 0 0 0-.5.5v3a.5.5 0 0 1-1 0v-3A1.5 1.5 0 0 1 1.5 0h3a.5.5 0 0 1 0 1h-3zM11 .5a.5.5 0 0 1 .5-.5h3A1.5 1.5 0 0 1 16 1.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 1-.5-.5zM.5 11a.5.5 0 0 1 .5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 1 0 1h-3A1.5 1.5 0 0 1 0 14.5v-3a.5.5 0 0 1 .5-.5zm15 0a.5.5 0 0 1 .5.5v3a1.5 1.5 0 0 1-1.5 1.5h-3a.5.5 0 0 1 0-1h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 1 .5-.5z"/>
                    <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm8-9a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                </svg>
                <div class="ms-3">
                    <p class="fw-bold mb-1"><c:out value="${user.name}"/></p>
                        <%--                    <p class="text-muted mb-0">john.doe@gmail.com</p>--%>
                </div>
            </div>
        </td>
        <td>
            <p class="fw-normal mb-1"><c:out value="${user.login}"/></p>
                <%--            <p class="text-muted mb-0">IT department</p>--%>
        </td>
        <td>
            <p class="fw-normal mb-1"><c:out value="${user.blocked}"/></p>
            <span class="badge badge-success rounded-pill d-inline"><c:out value="${user.blocked}"/></span>
        </td>
        <td>
            <form method="post" action="block">
                <input type="hidden" name="user_id" value="${user.id}">
                <c:choose>
                    <c:when test="${user.role == 'admin'}">
                        <button disabled type="submit" class="btn btn-secondary"><fmt:message key="button.block"/></button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-secondary"><fmt:message key="button.block"/></button>
                    </c:otherwise>
                </c:choose>
            </form>
        </td>
        <td>
            <form action="edit_user">
                <input type="hidden" name="user_id" value="${user.id}">
                <button type="submit" class="btn btn-secondary"><fmt:message key="button.edit"/></button>
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>


</body>
</html>
