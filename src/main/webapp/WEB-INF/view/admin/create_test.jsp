<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>quiz</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>
<form method="post" action="create_test">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="text" required placeholder="<fmt:message key="table.head.test.name"/>" name="name"
           value="${requestScope.name}"><br>
    <input type="text" required placeholder="<fmt:message key="table.head.subject"/>" name="subject"
           value="${requestScope.subject}"><br>
    <input type="number" required placeholder="<fmt:message key="table.head.difficult"/>" name="difficult"
           value="${requestScope.difficult}"><br>
    <input type="number" required placeholder="<fmt:message key="table.head.duration"/>" name="duration"
           value="${requestScope.duration}"><br>
    <input class="button" type="submit" value="<fmt:message key="button.create.test"/>">
</form>
<c:out value="${requestScope.message}"/>


<hr>

</body>
</html>
