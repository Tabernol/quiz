<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.12.2022
  Time: 01:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<html lang="${sessionScope.locale}">
<head>
    <title>Edit</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>
<form method="post" action="edit_test">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <fmt:message key="table.head.test.name"/>
    <input type="text" name="name" value="${requestScope.name}"><br>
    <fmt:message key="table.head.subject"/>
    <input type="text" name="subject" value="${requestScope.subject}"><br>
    <fmt:message key="table.head.difficult"/>
    <input type="number" name="difficult" value="${requestScope.difficult}"><br>
    <fmt:message key="table.head.duration"/>
    <input type="number" name="duration" value="${requestScope.duration}"><br>
    <input class="button" type="submit" value="<fmt:message key="button.edit"/>">
    <c:out value="${requestScope.message}"/>
</form>


<hr>
<form action="next_page">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>


<br>
<table class="sortable">
    <thead>
    <tr>
        <th><fmt:message key="table.head.text.of.question"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="question" items="${requestScope.questions}">
        <tr>
            <td><c:out value="${question.text}"/></td>
            <td>
                <form action="edit_question">
                    <input type="hidden" name="page" value="${requestScope.page}">
                    <input type="hidden" name="test_id" value="${requestScope.test_id}">
                    <input type="hidden" name="question_id" value="${question.id}">
                    <input class="button" type="submit" value="<fmt:message key="button.edit"/>">
                </form>

            </td>
            <td>
                <form method="post" action="delete_question">
                    <input type="hidden" name="page" value="${requestScope.page}">
                    <input type="hidden" name="test_id" value="${requestScope.test_id}">
                    <input type="hidden" name="question_id" value="${question.id}">
                    <input class="button" type="submit" value="<fmt:message key="button.delete"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<form method="post" action="add_question">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="text" required placeholder="<fmt:message key="table.head.text.of.question"/>" name="text"
           value="${requestScope.too_Long_Text}"><br>
    <input class="button" type="submit" value="<fmt:message key="button.add.question"/>">
    <c:out value="${requestScope.message_question}"/>
</form>

</body>
</html>
