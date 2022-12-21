<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<fmt:setLocale value="ua"/>
<fmt:setBundle basename="language"/>


<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
name = <c:out value="${sessionScope.name}"/>
<div class="form">
    <form method="post" action="login_form">
        <select name="lang">
            <option value="en"><c:out  value="english"/></option>
            <option value="ua"><c:out value="ukraine"/></option>
        </select><br>
        <input class="button" type="submit" value="choose">
    </form>
</div>
<br/>
<a href="<c:url value='/login_form'/>">Login</a>|
<a href="<c:url value='/registration'/>">Registration</a>|
<a href="<c:url value='/profile'/>">Profile</a>|
<a href="<c:url value='/logout'/>">Loggout</a>
<br>
<br>

<br>
<a href="<c:url value='/tests' />">All tests</a>




<c:forEach var="test" items="${sessionScope.tests}">
    <ul>
        <li>name: <c:out value="${test.name}"/></li>
        <li>difficult: <c:out value="${test.difficult}"/></li>
        <li>duration: <c:out value="${test.duration}"/></li>
    </ul>
    <%--    <a href="<c:url value='/test/redact'/>"    >Redact--%>
    <%--        <input type="hidden" name="testId" value="${test.id}">--%>
    <%--    </a><br>--%>
    <%--    <a href="<c:url value='/test'/>">Delete</a><br>--%>
    <%--    <a href="<c:url value='/test/cont'/>">Controller</a><br>--%>
    <%--    <a href="<c:url value='/test/add_q' />">Add question</a><br>--%>
    <hr/>
</c:forEach>

<br>


</body>
</html>