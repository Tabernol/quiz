<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>


<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="UTF-8">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <title>Hello Quiz</title>

<%--    <link rel="stylesheet" href="/static/css/style.css">--%>
<style>
    <%@include file="/static/css/style.css"%>

</style>

<%--    <script type="text/javascript">--%>
<%--        <%@include  file="/static/js/timer.js"%>--%>
<%--    </script>--%>

</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>
<h1 id="start_message"> <fmt:message key="label.start_message"/></h1>
<br>



<%--<div class="d-flex justify-content-center"> Center block</div>--%>


<div class="w-75 p-3">
    <div class="d-flex justify-content-center">
        <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3">Here is my text question</textarea>
        </div>
    </div>
</div>



</body>
</html>