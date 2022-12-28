<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 20.12.2022
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="form">
    <form method="post" action="login">
        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br>
        <input class="button" type="submit" value="login">
    </form>
    <c:out value="${requestScope.message}"/>

</div>

<%--<div class="form">--%>
<%--    <form method="post" action="login_form">--%>
<%--        <select name="lang">--%>
<%--            <option value="en"><c:out  value="english"/></option>--%>
<%--            <option value="ua"><c:out value="ukraine"/></option>--%>
<%--        </select><br>--%>
<%--        <input class="button" type="submit" value="choose">--%>
<%--    </form>--%>
<%--</div>--%>

<br>
<a href="<c:url value='/home'/>">back</a>
</body>
</html>
