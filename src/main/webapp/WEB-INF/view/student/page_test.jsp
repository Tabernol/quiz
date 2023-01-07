<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 29.12.2022
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Info test</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/language.jsp"/>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<div>
    <h5>Name  <c:out value="${requestScope.name}"/></h5>
    <h5>Subject  <c:out value="${requestScope.subject}"/></h5>
    <h5>Difficult  <c:out value="${requestScope.difficult}"/></h5>
    <h5>Duration  <c:out value="${requestScope.duration}"/></h5>
</div>

<h5> <c:out value="${requestScope.message}"/></h5>

<div>
    <form action="next_page">
        <input type="hidden" name="page" value="${requestScope.page}">
        <input class="button" type="submit" value="<fmt:message key="button.back"/>">
    </form>
</div>

<br>
<form action="start_test">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input class="button" type="submit" value="<fmt:message key="button.start.test"/>">
</form>

</body>
</html>
