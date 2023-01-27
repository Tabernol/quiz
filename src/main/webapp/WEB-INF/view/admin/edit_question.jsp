<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26.12.2022
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>edit question</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/view/header.jsp"/>

<form method="post" action="edit_question">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="hidden" name="question_id" value="${question.id}">
    <fmt:message key="table.head.text.of.question"/>
    <input type="text" name="text" value="${requestScope.question.text}"><br>
    <input class="button" type="submit" value="<fmt:message key="button.edit"/>">
    <c:out value="${requestScope.message}"/>
</form>

<hr>
<form action="edit_test">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>
<hr>


<table class="table table-success table-striped">
    <thead class="class bg-light">
    <tr>
        <th><fmt:message key="table.head.text.of.answer"/></th>
        <th><fmt:message key="table.head.result.answer"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="answer" items="${requestScope.answers}">
    <tr>
        <td><c:out value="${answer.text}"/></td>
        <td><c:out value="${answer.result}"/></td>
        <td>
            <form method="post" action="delete_answer">
                <input type="hidden" name="page" value="${requestScope.page}">
                <input type="hidden" name="test_id" value="${requestScope.test_id}">
                <input type="hidden" name="question_id" value="${requestScope.question.id}">
                <input type="hidden" name="answer_id" value="${answer.id}">
                <input class="button btn-secondary" type="submit" value="<fmt:message key="button.delete"/>">
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>


<form method="post" action="add_answer">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="hidden" name="question_id" value="${requestScope.question.id}">
    <input type="text" required placeholder="<fmt:message key="table.head.text.of.answer"/>"
           name="text" value="${requestScope.too_long_answer}"><br>
    <input type="radio" name="result" value="true"><fmt:message key="select.option.true"/> <br>
    <input type="radio" name="result" value="false"><fmt:message key="select.option.false"/> <br>
    <input class="button" type="submit" value="<fmt:message key="button.add.answer"/>">
    <c:out value="${requestScope.message_answer}"/>
</form>

</body>
</html>
