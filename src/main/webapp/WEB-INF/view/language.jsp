<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26.12.2022
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Language</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>
<%--<select id="selectBox" onchange="changeFunc(value);">--%>
<%--  <option value="en">en</option>--%>
<%--  <option value="ua">ua</option>--%>
<%--</select>--%>
<div id="lang">
    <form method="post" action="language">
        <select name="lang">
            <option value="en"><fmt:message key="language.en"/></option>
            <option value="ua"><fmt:message key="language.ukr"/></option>
        </select><br>
        <input type="submit" value="<fmt:message key="button.choose"/>">
    </form>
</div>
</body>
</html>
