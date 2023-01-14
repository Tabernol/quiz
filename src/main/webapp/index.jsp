<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


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

    <script type="text/javascript">
        <%@include  file="/static/js/timer.js"%>
    </script>

</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>
<h1 id="start_message"> <fmt:message key="label.start_message"/></h1>
<br>
<div>Registration closes in <span id="time">05:00</span> minutes!</div>

</body>
</html>