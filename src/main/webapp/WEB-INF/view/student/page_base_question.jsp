<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10.01.2023
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Title</title>

    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body class="gen">
<%--<jsp:include page="/WEB-INF/view/header.jsp"/>--%>
<br>
<br>
<jsp:include page="/WEB-INF/view/student/timer.jsp"/>
<br>
<jsp:include page="/WEB-INF/view/student/page_question.jsp"/>

</body>
</html>
