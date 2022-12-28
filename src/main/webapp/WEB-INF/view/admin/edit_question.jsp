<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26.12.2022
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit question</title>
</head>
<body>

<form method="post" action="edit_question">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="hidden" name="question_id" value="${question.id}">
    text <input type="text" name="text" value="${requestScope.question.text}"><br>
    <input class="button" type="submit" value="Edit question">
</form>

<table class="sortable">
    <thead>
    <tr>
        <th>Text</th>
        <th>Result</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="answer" items="${requestScope.answers}">
        <tr>
            <td><c:out value="${answer.text}"/></td>
            <td><c:out value="${answer.result}"/></td>
            <td>
                <form method="post" action="delete_answer">
                    <input type="hidden" name="test_id" value="${requestScope.test_id}">
                    <input type="hidden" name="question_id" value="${requestScope.question.id}">
                    <input type="hidden" name="answer_id" value="${answer.id}">
                    <input class="button" type="submit" value="delete answer">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form method="post" action="add_answer">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="hidden" name="question_id" value="${requestScope.question.id}">
    <input type="text" required placeholder="text of answer" name="text"><br>
    <input type="radio" name="result" value="true">True<br>
    <input type="radio" name="result" value="false">False<br>
    <input class="button" type="submit" value="Add Answer">
</form>

</body>
</html>