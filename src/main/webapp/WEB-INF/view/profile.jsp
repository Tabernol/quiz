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
<%--=========================--%>
<div class="offcanvas offcanvas-start" id="forTest">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title"><fmt:message key="button.edit.profile"/></h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="edit_user">
            <input type="hidden" name="page" value="${requestScope.page}">
            <input type="hidden" name="user_id" value="${requestScope.user.id}">
            <h6 class="fw-light"><fmt:message key="table.head.name"/></h6>
            <input type="text" name="name" value="${requestScope.user.name}">
            <c:if test="${sessionScope.role == 'admin'}">
                <h6 class="fw-light"><fmt:message key="label.role"/></h6>
                <input type="text" name="role" value="${requestScope.user.role}">
            </c:if>
            <div class="d-flex justify-content-center">
                <button type="submit"
                        class="btn btn-secondary"><fmt:message key="button.edit"/>
                </button>
            </div>
        </form>
    </div>
</div>
<!-- Button to open the editProfile sidebar -->
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forTest">
    <fmt:message key="button.edit.profile"/>
</button>

<h3><c:out value="${requestScope.message}"/></h3>
<%--========================--%>


<c:if test="${requestScope.user_result != null}">
    <%--==================================--%>
    <div class="d-flex flex-row justify-content-center">
        <form>
            <input type="hidden" name="user_id" value="${requestScope.user.id}">
            <div class="input-group">
                <div class="p-2 bd-highlight">
                    <select class="form-select" name="sub">
                        <option value="all"><c:out value="all"/></option>
                        <c:forEach var="sub" items="${sessionScope.subjects}">
                            <option value="" disabled selected hidden><c:out value="${sessionScope.sub}"/></option>
                            <option value="${sub}"><c:out value="${sub}"/></option>
                        </c:forEach>
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
                        <option value="difficult asc"><fmt:message key="sort.difficult.asc"/></option>
                        <option value="difficult desc"><fmt:message key="sort.difficult.desc"/></option>
                        <option value="date asc"><fmt:message key="sort.time.asc"/></option>
                        <option value="date desc"><fmt:message key="sort.time.desc"/></option>
                    </select>
                </div>
                <div class="p-2 bd-highlight">
                    <input class="btn btn-primary" type="submit" value="<fmt:message key="button.filter"/>">
                </div>
            </div>
        </form>
    </div>


    <table class="table align-middle mb-0 w-auto bg-white">
        <div class="d-flex justify-content-around">
            <form action="download">
                <input type="hidden" name="user_id" value="${requestScope.user.id}">
                <button type="submit"
                        class="btn btn-primary"><fmt:message key="button.download"/>
                </button>
            </form>
        </div>
        <thead class="bg-light">
        <tr>
            <th><fmt:message key="table.head.test.name"/></th>
            <th><fmt:message key="table.head.subject"/></th>
            <th><fmt:message key="table.head.difficult"/></th>
            <th><fmt:message key="table.head.duration"/></th>
            <th><fmt:message key="table.head.grade"/></th>
            <th><fmt:message key="table.head.date"/></th>
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
            <td><c:out value="${result.date}"/></td>
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
                        <c:param name="user_id" value="${requestScope.user.id}"/>
                    </c:url>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</c:if>


</body>
</html>
