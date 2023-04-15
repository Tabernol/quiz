<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 22.02.2023
  Time: 01:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Image</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<c:choose>
    <c:when test="${requestScope.message_bad_request != null}">
        <div class="alert alert-warning">
            <strong>Warning!</strong> <c:out value="${requestScope.message_bad_request}"/>
        </div>
    </c:when>
    <c:when test="${requestScope.message_success != null}">
        <div class="alert alert-success">
            <strong>Success!</strong> <c:out value="${requestScope.message_success}"/>
        </div>
    </c:when>
</c:choose>





<div class="offcanvas offcanvas-start" id="forAddImage">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title"></h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="load" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit" value="<fmt:message key="button.load"/>">
        </form>
    </div>
</div>

<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forAddImage">
    <fmt:message key="button.load.image"/>
</button>

<c:choose>
    <c:when test="${requestScope.question_id != null}">
        <table class="table align-middle mb-0 w-auto bg-white">
            <thead class="bg-secondary">
            <tr>
                <th><fmt:message key="table.head.image"/> </th>
                <th><fmt:message key="table.head.width"/></th>
                <th><fmt:message key="table.head.height"/></th>
                <th><fmt:message key="table.head.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="image" items="${requestScope.images}">
            <tr>
                <td>
                    <a href="${image.url}">
                        <img src="${image.url}" alt="icon" width="150" height="150">
                    </a>
                </td>
                <td><c:out value="${image.width}"/></td>
                <td><c:out value="${image.height}"/></td>
                <td>
                    <form method="post" action="update_image">
                        <input type="hidden" name="page" value="${requestScope.page}">
                        <input type="hidden" name="test_id" value="${requestScope.test_id}">
                        <input type="hidden" name="question_id" value="${requestScope.question_id}">
                        <input type="hidden" name="url" value="${image.url}">
                        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.add.image"/>">
                    </form>
                </td>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <table class="table align-middle mb-0 w-auto bg-white">
            <thead class="bg-secondary">
            <tr>
                <th><fmt:message key="table.head.image"/> </th>
                <th><fmt:message key="table.head.width"/></th>
                <th><fmt:message key="table.head.height"/></th>
                <th><fmt:message key="table.head.action"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="image" items="${requestScope.images}">
            <tr>
                <td>
                    <a href="${image.url}">
                        <img src="${image.url}" alt="icon" width="150" height="150">
                    </a>
                </td>
                <td><c:out value="${image.width}"/></td>
                <td><c:out value="${image.height}"/></td>
                <td>
                    <form method="post" action="delete_image">
                        <input type="hidden" name="public_id" value="${image.publicId}">
                        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.remove.image"/>">
                    </form>
                </td>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>
