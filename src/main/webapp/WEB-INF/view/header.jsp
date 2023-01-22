<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 06.01.2023
  Time: 01:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC
+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <%--    <style>--%>
    <%--        <%@include file="/static/css/style.css"%>--%>
    <%--    </style>--%>

</head>
<body class="gen">

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <%--                <a class="navbar-brand" href="#">Navbar</a>--%>
        <%--                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"--%>
        <%--                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">--%>
        <%--                    <span class="navbar-toggler-icon"></span>--%>
        <%--                </button>--%>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <c:if test="${sessionScope.role == null}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/login_form'/>"><fmt:message key="label.Login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/registration'/>"><fmt:message key="label.Registration"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'admin'}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/users' />"><fmt:message key="label.users"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/filter_tests'>
                                    <c:param name="order" value="name asc" />
                                    <c:param name="sub" value="all"/>
                                    <c:param name="rows" value="5"/>
                                    <c:param name="page" value="1"/>
                                    </c:url>">
                            <fmt:message key="label.tests"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/to_create_test'/>"><fmt:message key="label.create.test"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/profile'/>"><fmt:message key="label.Profile"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/logout'/>"><fmt:message key="label.Logout"/></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.role == 'student'}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/filter_tests'>
                            <c:param name="order" value="name asc" />
                            <c:param name="sub" value="all"/>
                            <c:param name="rows" value="5"/>
                            <c:param name="page" value="1"/>
                        </c:url>">
                            <fmt:message key="label.tests"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/profile' />"><fmt:message key="label.Profile"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="<c:url value='/logout' />"><fmt:message key="label.Logout"/></a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div>
    <jsp:include page="/WEB-INF/view/language.jsp"/>
</div>

</body>
</html>
