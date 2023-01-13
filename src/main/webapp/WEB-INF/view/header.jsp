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


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Header</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>

</head>
<body class="gen">
<nav class="navMenu" id="header">
    <div>
        <c:if test="${sessionScope.role == null}">
            <a class="button_my" href="<c:url value='/login_form'/>"><fmt:message key="label.Login"/></a>|
            <a class="button_my" href="<c:url value='/registration'/>"><fmt:message key="label.Registration"/></a>|
        </c:if>


        <c:if test="${sessionScope.role == 'admin'}">
            <a class="button_my" href="<c:url value='/users' />"><fmt:message key="label.users"/> </a>|
            <a class="button_my" href="<c:url value='/filter_tests'>
  <c:param name="order" value="name asc" />
    <c:param name="sub" value="all"/>
    <c:param name="rows" value="5"/>
    <c:param name="page" value="1"/>
</c:url>"><fmt:message key="label.tests"/></a>|
            <a class="button_my" href="<c:url value='/to_create_test'/>"><fmt:message key="label.create.test"/> </a>|
            <a class="button_my" href="<c:url value='/profile'/>"><fmt:message key="label.Profile"/> </a>|
            <a class="button_my" href="<c:url value='/logout'/>"><fmt:message key="label.Logout"/> </a>
        </c:if>


        <c:if test="${sessionScope.role == 'student'}">
            <a class="button_my" href="<c:url value='/filter_tests'>
  <c:param name="order" value="name asc" />
    <c:param name="sub" value="all"/>
    <c:param name="rows" value="5"/>
    <c:param name="page" value="1"/>
</c:url>"><fmt:message key="label.tests"/> </a>|
            <a class="button_my" href="<c:url value='/profile' />"><fmt:message key="label.Profile"/> </a>|
            <a class="button_my" href="<c:url value='/logout' />"><fmt:message key="label.Logout"/> </a>
        </c:if>
    </div>
</nav>

<div>
    <jsp:include page="/WEB-INF/view/language.jsp"/>
</div>

</body>
</html>
