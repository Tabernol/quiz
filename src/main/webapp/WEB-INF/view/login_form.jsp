<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 20.12.2022
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/language.jsp"/>
<div class="form">
    <form method="post" action="login">
        <input type="text" required placeholder="<fmt:message key="label.login"/>" name="login"><br>
        <input type="password" required placeholder="<fmt:message key="label.password"/>" name="password"><br>
        <input class="button" type="submit" value="<fmt:message key="label.Login"/>">
    </form>
    <c:out value="${requestScope.message}"/>

</div>

<br>
<a href="<c:url value='/index.jsp'/>"><fmt:message key="button.back"/></a>
</body>
</html>
