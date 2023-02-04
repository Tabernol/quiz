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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/view/header.jsp"/>

<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>

<h4>
    <fmt:message key="label.name"/> <c:out value="${sessionScope.name}"/><br>
</h4>

<%--=========================--%>
<div class="offcanvas offcanvas-start" id="forTest">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title">Edit profile</h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="edit_profile">
            <input type="hidden" name="page" value="${requestScope.page}">

            <h6 class="fw-light"><fmt:message key="table.head.name"/></h6>
            <input type="text" name="name" value="${sessionScope.name}">
            <div class="d-flex justify-content-center">
                <button type="submit"
                        class="btn btn-secondary"><fmt:message key="button.edit"/>
                </button>
            </div>
        </form>
    </div>
</div>
<!-- Button to open the offcanvas sidebar -->
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forTest">
    Edit profile
</button>

<h3><c:out value="${requestScope.message}"/></h3>
<%--========================--%>


<c:if test="${sessionScope.role == 'student'}">
    <div>
        <form action="filter_result">
            <input type="hidden" name="page" value="1">
            <select name="sub">
                <option value="all"><c:out value="all"/></option>
                <c:forEach var="sub" items="${sessionScope.subjects}">
                    <option value="${sub}"><c:out value="${sub}"/></option>
                </c:forEach>
            </select>
            <select name="order">
                <option value="name asc"><fmt:message key="sort.name.asc"/></option>
                <option value="name desc"><fmt:message key="sort.name.desc"/></option>
                <option value="difficult asc"><fmt:message key="sort.difficult.asc"/></option>
                <option value="difficult desc"><fmt:message key="sort.difficult.desc"/></option>
                <option value="popularity asc"><fmt:message key="sort.popularity.asc"/></option>
                <option value="popularity desc"><fmt:message key="sort.popularity.desc"/></option>
            </select>
            <select name="rows">
                <option value="2"><c:out value="2"/></option>
                <option value="5"><c:out value="5"/></option>
                <option value="10"><c:out value="10"/></option>
                <option value="20"><c:out value="20"/></option>
            </select><br>
            <input class="button" type="submit" value="<fmt:message key="button.filter"/>">
        </form>
    </div>

    <form action="download">
        <div class="d-flex justify-content-center">
            <button type="submit"
                    class="btn btn-secondary">DownLoad</button>
        </div>
    </form>
    <%--==================================--%>
    <table class="table align-middle mb-0 w-auto bg-white">
        <thead class="bg-light">
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
            </c:forEach>
        </tbody>
    </table>

    <%--================================--%>
    <div class="center">
        <nav aria-label="pagination-sm">
            <ul class="pagination pagination-sm">
                <c:forEach var="i" begin="1" end="${requestScope.count_pages}">
                    <li class="page-item"><a class="page-link"
                                             href="<c:url value='/filter_result'>
                        <c:param name="page" value="${i}"/>
                    </c:url>"><c:out value="${i}"/></a></li>
                </c:forEach>
                    <%--            <li class="page-item active" aria-current="page">--%>
                    <%--                <span class="page-link">1</span>--%>
                    <%--            </li>--%>
            </ul>
        </nav>
    </div>
</c:if>


</body>
</html>
