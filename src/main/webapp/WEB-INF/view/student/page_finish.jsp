<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 01.01.2023
  Time: 01:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Finish</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<h2><tags:grade grade="${requestScope.percent_result}"></tags:grade></h2>

<fmt:message key="label.quiz.completed.message"/> <ctg:number></ctg:number>


<hr>
<form action="home">
    <input class="button" type="submit" value="Home">
</form>

</body>
</html>
