<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="customtags" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <title>Hello Quiz</title>

<%--    <link rel="stylesheet" href="/static/css/style.css">--%>
<style>
    <%@include file="/static/css/style.css"%>

</style>

</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<%--<jsp:include page="/WEB-INF/view/language.jsp"/>--%>
<br>
<h1 id="start_message"> <fmt:message key="label.start_message"/></h1>
<br>

<ctg:today/>

</body>
</html>