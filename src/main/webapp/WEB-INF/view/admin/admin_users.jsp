<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE>
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

<div class="d-flex flex-row justify-content-center">
    <form>
        <div class="input-group">
            <div class="p-2 bd-highlight">
                <select class="form-select" name="status">
                    <option value="" disabled selected hidden><c:out value="${sessionScope.status}"/></option>
                    <option value="all"><c:out value="all"/></option>
                    <option value="true"><c:out value="blocked"/></option>
                    <option value="false"><c:out value="unblocked"/></option>
                </select>
            </div>
            <div class="p-2 bd-highlight">
                <select class="form-select" name="rows">
                    <option value="" disabled selected hidden><c:out value="${sessionScope.rows}"/></option>
                    <option value="2"><c:out value="2"/></option>
                    <option value="5"><c:out value="5"/></option>
                    <option value="10"><c:out value="10"/></option>
                    <option value="20"><c:out value="20"/></option>
                </select>
            </div>
            <div class="p-2 bd-highlight">
                <select class="form-select" name="order">
                    <option value="" disabled selected hidden><c:out value="${sessionScope.order}"/></option>
                    <option value="name asc"><fmt:message key="sort.name.asc"/></option>
                    <option value="name desc"><fmt:message key="sort.name.desc"/></option>
                </select>
            </div>
            <div class="p-2 bd-highlight">
                <input class="btn btn-primary" type="submit" value="<fmt:message key="button.filter"/>">
            </div>
        </div>
    </form>
</div>

<table class="table align-middle mb-0 w-auto bg-white">
    <thead class="bg-secondary">
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
        <td>
            <div class="d-flex align-items-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-person-bounding-box" viewBox="0 0 16 16">
                    <path d="M1.5 1a.5.5 0 0 0-.5.5v3a.5.5 0 0 1-1 0v-3A1.5 1.5 0 0 1 1.5 0h3a.5.5 0 0 1 0 1h-3zM11 .5a.5.5 0 0 1 .5-.5h3A1.5 1.5 0 0 1 16 1.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 1-.5-.5zM.5 11a.5.5 0 0 1 .5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 1 0 1h-3A1.5 1.5 0 0 1 0 14.5v-3a.5.5 0 0 1 .5-.5zm15 0a.5.5 0 0 1 .5.5v3a1.5 1.5 0 0 1-1.5 1.5h-3a.5.5 0 0 1 0-1h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 1 .5-.5z"/>
                    <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm8-9a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                </svg>
                <div class="ms-3">
                    <p class="fw-bold mb-1"><c:out value="${user.name}"/></p>
                </div>
            </div>
        </td>
        <td>
            <p class="fw-normal mb-1"><c:out value="${user.login}"/></p>
        </td>
        <td>
            <p class="fw-normal mb-1"><c:out value="${user.blocked}"/></p>
        </td>
        <td>
            <form method="post" action="block">
                <input type="hidden" name="user_id" value="${user.id}">
                <input type="hidden" name="page" value="${requestScope.page}">
                <c:choose>
                    <c:when test="${user.role == 'admin'}">
                        <button disabled type="submit" class="btn btn-secondary"><fmt:message
                                key="button.block"/></button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-secondary"><fmt:message key="button.block"/></button>
                    </c:otherwise>
                </c:choose>
            </form>
        </td>
        <td>
            <form action="profile">
                <input type="hidden" name="user_id" value="${user.id}">
                <button type="submit" class="btn btn-secondary"><fmt:message key="button.info"/></button>
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>

<div class="center">
    <nav aria-label="pagination-sm">
        <ul class="pagination pagination-sm">
            <c:forEach var="i" begin="1" end="${requestScope.count_pages}">
                <li class="page-item"><a class="page-link"
                                         href="<c:url value='/filter_users'>
                        <c:param name="page" value="${i}"/>
                    </c:url>"><c:out value="${i}"/></a></li>
            </c:forEach>

        </ul>
    </nav>
</div>
</body>
</html>
