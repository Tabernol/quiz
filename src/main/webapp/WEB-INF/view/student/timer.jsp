
<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 03.01.2023
  Time: 00:58
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
    <script type="text/javascript">
        <%@include  file="/static/js/timer.js"%>
    </script>


</head>
<body>
<div>Test closes in <span id="timer">05:00</span> minutes!</div>
<%--<div>Test closes in <span id="time"><c:out value="${requestScope.duration}"/></span> minutes!</div>--%>
<%--<h3><c:out value="${requestScope.duration}"/></h3>--%>
<%--Registration closes in <span id="timer">5:00</span>!--%>
<%--<script>--%>
<%--    function startTimer(duration, display) {--%>
<%--        var timer = duration, minutes, seconds;--%>
<%--        setInterval(function () {--%>
<%--            minutes = parseInt(timer / 60, 10);--%>
<%--            seconds = parseInt(timer % 60, 10);--%>

<%--            minutes = minutes < 10 ? "0" + minutes : minutes;--%>
<%--            seconds = seconds < 10 ? "0" + seconds : seconds;--%>

<%--            display.textContent = minutes + ":" + seconds;--%>

<%--            if (--timer < 0) {--%>
<%--                timer = duration;--%>
<%--            }--%>
<%--        }, 1000);--%>
<%--    }--%>


<%--    window.onload = function () {--%>
<%--        let duration;--%>
<%--        duration = ${requestScope.duration}--%>
<%--            duration = 60 * dur,--%>
<%--            display = document.querySelector('time');--%>
<%--        startTimer(duration, display);--%>
<%--    };--%>
<%--</script>--%>
</body>
</html>
