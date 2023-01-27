<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26.12.2022
  Time: 02:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>edit user</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>
<form method="post" action="edit_user">
    <input type="hidden" name="user_id" value="${requestScope.user.id}">
    <fmt:message key="label.name"/> <input type="text" name="name" value="${requestScope.user.name}"><br>
    <fmt:message key="label.role"/> <input type="text" name="role" value="${requestScope.user.role}"><br>

    <input class="button" type="submit" value="<fmt:message key="button.edit"/>">
    <c:out value="${requestScope.message}"/>
</form>

<form method="post" action="delete_user">
    <input type="hidden" name="user_id" value="${user.id}">
    <input class="button" type="submit" value=" <fmt:message key="button.delete"/>">
</form>

<form action="users">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>

</body>
</html>
