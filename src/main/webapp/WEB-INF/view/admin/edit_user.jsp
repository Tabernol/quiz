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


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>edit user</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>
<form method="post" action="edit_user">
    <input type="hidden" name="user_id" value="${requestScope.user.id}">
    Name <input type="text" name="name" value="${requestScope.user.name}"><br>
    Login <input type="text" name="login" value="${requestScope.user.login}"><br>
    Role <select name="role">
    <option selected value="${requestScope.user.role}"></option>
    <option value="student"> student</option>
    <option value="admin"> admin</option>
</select><br>
    Status <select name="status">
    <option value="false"><fmt:message key="select.option.no"/></option>
    <option value="true"><fmt:message key="select.option.yes"/></option>
</select>
    <br>

    <%--    <input type="text" name="status" value="${requestScope.user.blocked}"><br>--%>
    <input class="button" type="submit" value="<fmt:message key="button.edit.user"/>">
</form>

<form action="users">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>

</body>
</html>
