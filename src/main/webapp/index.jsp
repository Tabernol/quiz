<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>


<!DOCTYPE html>
<html>
<head>
    <title>Hello Quiz</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/language.jsp"/>
<br/>
<h1> <fmt:message key="label.start_message"/></h1>
<a href="<c:url value='/login_form'/>"><fmt:message key="label.Login"/></a>|
<a href="<c:url value='/registration'/>"><fmt:message key="label.registration"/></a>|
<%--<a href="<c:url value='/profile'/>">Profile</a>|--%>
<%--<a href="<c:url value='/logout'/>">Loggout</a>--%>
<br>
<br>


</body>
</html>