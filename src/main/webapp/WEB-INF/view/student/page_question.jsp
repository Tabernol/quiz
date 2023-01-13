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


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ctg:number></ctg:number>

<c:out value="${requestScope.text}"/>

<hr>
<form method="post" action="result_answer">
    <input type="hidden" name="id_question" value="${requestScope.id_question}">
    <input type="hidden" name="number_question" value="${requestScope.number_question}">
    <c:forEach var="ans" items="${requestScope.answers}">
        <input  type="hidden" name="res" value="${ans.id}">
        <input type="checkbox" name="res">
        <c:out value="${ans.text}"/>
        <br>
    </c:forEach>
    <input class="button" type="submit" value="Yes">
</form>
<hr>


</body>
</html>
