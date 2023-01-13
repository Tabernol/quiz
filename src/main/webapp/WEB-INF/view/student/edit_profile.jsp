<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 07.01.2023
  Time: 03:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>
<form method="post" action="edit_user_profile">
    <input type="hidden" name="user_id" value="${sessionScope.user_id}">
    <fmt:message key="label.name"/> <input type="text" name="name" value="${requestScope.name}"><br>
    <input class="button" type="submit" value="<fmt:message key="button.edit.user"/>">
    <c:out value="${requestScope.message}"/>
</form>

</body>
</html>
