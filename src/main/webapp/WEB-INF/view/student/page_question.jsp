<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 29.12.2022
  Time: 04:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Title</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"/>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"/>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
          rel="stylesheet"/>

    <!-- MDB -->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div id="timer"> </div>


<div id="text_question">
    <input id="id_question" type="hidden" name="id_question" value="${requestScope.id_question}">
    <input id="number_question" type="hidden" name="number_question" value="${requestScope.number_question}">
</div>

<%--<form method="post" action="result_answer">--%>
<%--    <c:forEach var="ans" items="${requestScope.answers}">--%>
<%--        <input class="res" type="checkbox" name="res" value="${ans.id}">--%>
<%--        <c:out value="${ans.text}"/>--%>
<%--        <br>--%>
<%--    </c:forEach>--%>
<%--        <input class="button" type="submit" value="<fmt:message key="select.option.yes"/>">--%>
<%--</form>--%>


<%--<div class="mb-3">--%>
<%--    <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>--%>
<%--    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>--%>
<%--</div>--%>




<script>
    window.onload = function () {
        loadQuestionAndAnswer(
            document.getElementById('id_question').value,
            document.getElementById('number_question').value,
            document.getElementsByName('res'));

        timer(${requestScope.duration})
    }
</script>



</body>
</html>
