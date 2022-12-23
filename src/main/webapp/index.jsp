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
name = <c:out value="${sessionScope.name}"/>
<div class="form">
    <form method="post" action="language">
        <select name="lang">
            <option value="en"><c:out value="english"/></option>
            <option value="ua"><c:out value="ukraine"/></option>
        </select><br>
        <input class="button" type="submit" value="choose">
    </form>
</div>
<br/>
<h1> Do you want to take test?</h1>
<a href="<c:url value='/login_form'/>"><fmt:message key="label.login"/></a>|
<a href="<c:url value='/registration'/>">Registration</a>|
<%--<a href="<c:url value='/profile'/>">Profile</a>|--%>
<%--<a href="<c:url value='/logout'/>">Loggout</a>--%>
<br>
<br>


</body>
</html>