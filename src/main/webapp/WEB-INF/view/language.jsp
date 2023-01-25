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

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
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

<select class="selectpicker" data-width="fit">
    <option data-content='<span class="flag-icon flag-icon-us"></span> English'>English</option>
    <option  data-content='<span class="flag-icon flag-icon-mx"></span> Ukraine'>Ukraine</option>
</select>




<div id="lang">
    <form action="language">
        <select name="locale">
            <option value="en"><fmt:message key="language.en"/></option>
            <option value="ua"><fmt:message key="language.ukr"/></option>
        </select><br>
        <input type="submit" value="<fmt:message key="button.choose"/>">
    </form>
</div>


<script>
    $(function(){
        $('.selectpicker').selectpicker();
    });
</script>



</body>
</html>
