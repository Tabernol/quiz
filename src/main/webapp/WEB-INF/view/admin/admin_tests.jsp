<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.12.2022
  Time: 00:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Tests</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>

<div class="offcanvas offcanvas-start" id="AddTest">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title"><fmt:message key="button.create.test"/> </h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="create_test">
            <input type="hidden" name="page" value="${requestScope.page}">
            <input type="text" required placeholder="<fmt:message key="table.head.test.name"/>" name="name"
                   value="${requestScope.name}"><br>
            <input type="text" required placeholder="<fmt:message key="table.head.subject"/>" name="subject"
                   value="${requestScope.subject}"><br>
            <input type="number" required placeholder="<fmt:message key="table.head.difficult"/>" name="difficult"
                   value="${requestScope.difficult}"><br>
            <input type="number" required placeholder="<fmt:message key="table.head.duration"/>" name="duration"
                   value="${requestScope.duration}"><br>
            <div class="d-flex justify-content-center">
                <button type="submit"
                        class="btn btn-secondary"><fmt:message key="button.create"/>
                </button>
            </div>
            <%--            <c:out value="${requestScope.message}"/>--%>
        </form>
    </div>
</div>
<!-- Button to open the offcanvas sidebar -->
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#AddTest">
    <fmt:message key="button.create.test"/>
</button>


<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>


<div class="d-flex flex-row justify-content-center">
    <form>
        <div class="input-group">
            <div class="p-2 bd-highlight">
                <select class="form-select" name="sub">
                    <option value="all"><c:out value="all"/></option>
                    <c:forEach var="sub" items="${sessionScope.subjects}">
                        <option value="${sub}"><c:out value="${sub}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="p-2 bd-highlight">
                <select class="form-select" name="rows">
                    <option value="2"><c:out value="2"/></option>
                    <option value="5"><c:out value="5"/></option>
                    <option value="10"><c:out value="10"/></option>
                    <option value="20"><c:out value="20"/></option>
                </select>
            </div>
            <div class="p-2 bd-highlight">
                <select class="form-select" name="order">
                    <option value="name asc"><fmt:message key="sort.name.asc"/></option>
                    <option value="name desc"><fmt:message key="sort.name.desc"/></option>
                    <option value="difficult asc"><fmt:message key="sort.difficult.asc"/></option>
                    <option value="difficult desc"><fmt:message key="sort.difficult.desc"/></option>
                    <option value="popularity asc"><fmt:message key="sort.popularity.asc"/></option>
                    <option value="popularity desc"><fmt:message key="sort.popularity.desc"/></option>
                </select>
            </div>
            <div class="p-2 bd-highlight">
                <input class="btn btn-primary" type="submit" value="<fmt:message key="button.filter"/>">
            </div>
        </div>
    </form>
</div>

<c:out value="${requestScope.message}"/>

<table class="table align-middle mb-0 w-auto bg-white">
    <thead class="bg-secondary">
    <tr>
        <th><fmt:message key="table.head.test.name"/></th>
        <th><fmt:message key="table.head.subject"/></th>
        <th><fmt:message key="table.head.difficult"/></th>
        <th><fmt:message key="table.head.duration"/></th>
        <th><fmt:message key="table.head.popularity"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="test" items="${sessionScope.tests}">
    <tr>
        <td><c:out value="${test.name}"/></td>
        <td><c:out value="${test.subject}"/></td>
        <td><c:out value="${test.difficult}"/></td>
        <td><c:out value="${test.duration}"/></td>
        <td><c:out value="${test.popularity}"/></td>
        <td>
            <form action="edit_test">
                <input type="hidden" name="test_id" value="${test.id}">
                <input type="hidden" name="page" value="${requestScope.page}">
                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.edit"/>">
            </form>
        </td>
        <td>
            <form method="post" action="delete_test">
                <input type="hidden" name="page" value="${requestScope.page}">
                <input type="hidden" name="test_id" value="${test.id}">
                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.delete"/>">
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
                                         href="<c:url value='/filter_tests'>
                        <c:param name="page" value="${i}"/>
                    </c:url>"><c:out value="${i}"/></a></li>
            </c:forEach>
            <%--            <li class="page-item active" aria-current="page">--%>
            <%--                <span class="page-link">1</span>--%>
            <%--            </li>--%>
        </ul>
    </nav>
</div>

<%--<c:forEach var="i" begin="1" end="${requestScope.count_pages}">--%>
<%--    <a href="<c:url value='/next_page'>--%>
<%--                        <c:param name="order" value="${requestScope.order}"/>--%>
<%--                        <c:param name="sub" value="${requestScope.sub}"/>--%>
<%--                        <c:param name="rows" value="${requestScope.rows}"/>--%>
<%--                        <c:param name="page" value="${i}"/>--%>
<%--                    </c:url>"> <c:out value="${i}"/> </a>--%>
<%--</c:forEach>--%>


</body>
</html>
