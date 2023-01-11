<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 20.12.2022
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Registration</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>


</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<div class="form">
    <form method="post" action="registration">
        <input type="text" required placeholder="<fmt:message key="label.name"/>" name="name"><br>
        <input type="text" required placeholder="<fmt:message key="label.login"/>" name="login"><br>
        <input type="password" required placeholder="<fmt:message key="label.password"/>" name="password"><br>
        <input class="button" type="submit" value="<fmt:message key="label.Registration"/>">
    </form>
</div>
<c:out value="${requestScope.message}"/>

<br>
<a href="<c:url value='/index.jsp' />"><fmt:message key="button.back"/></a>





</body>
</html>
