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


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Info test</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<div>
    <h5><fmt:message key="table.head.test.name"/> <c:out value="${requestScope.name}"/></h5>
    <h5><fmt:message key="table.head.subject"/> <c:out value="${requestScope.subject}"/></h5>
    <h5><fmt:message key="table.head.difficult"/> <c:out value="${requestScope.difficult}"/></h5>
    <h5><fmt:message key="table.head.duration"/> <c:out value="${requestScope.duration}"/></h5>
</div>


<div>
    <form action="next_page">
        <input type="hidden" name="page" value="${requestScope.page}">
        <input class="button" type="submit" value="<fmt:message key="button.back"/>">
    </form>
</div>

<br>
<c:if test="${requestScope.message == null}">
    <form action="start_test">
        <input type="hidden" name="page" value="${requestScope.page}">
        <input type="hidden" name="test_id" value="${requestScope.test_id}">
        <input type="hidden" name="duration" value="${requestScope.duration}">
        <input class="button" type="submit" value="<fmt:message key="button.start.test"/>">
    </form>
</c:if>
<c:if test="${requestScope.message != null}">
    <fmt:message key="label.empty.test.message"/>
</c:if>


</body>
</html>
