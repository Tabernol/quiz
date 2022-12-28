<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.12.2022
  Time: 01:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<br>
<form method="post" action="edit_test">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    name <input type="text" name="name" value="${requestScope.name}"><br>
    subject <input type="text" name="subject" value="${requestScope.subject}"><br>
    difficult <input type="number" name="difficult" value="${requestScope.difficult}"><br>
    duration <input type="number" name="duration" value="${requestScope.duration}"><br>
    <input class="button" type="submit" value="Edit test">
</form>


<hr>
<br>
<table class="sortable">
    <thead>
    <tr>
        <th>Text</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="question" items="${requestScope.questions}">
        <tr>
            <td><c:out value="${question.text}"/></td>
            <td>
                <form action="edit_question">
                    <input type="hidden" name="test_id" value="${requestScope.test_id}">
                    <input type="hidden" name="question_id" value="${question.id}">
                    <input class="button" type="submit" value="edit question">
                </form>

            </td>
            <td>
                <form method="post" action="delete_question">
                    <input type="hidden" name="test_id" value="${requestScope.test_id}">
                    <input type="hidden" name="question_id" value="${question.id}">
                    <input class="button" type="submit" value="delete question">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<form method="post" action="add_question">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="text" required placeholder="text of question" name="text"><br>
    <input class="button" type="submit" value="Add question">
</form>

</body>
</html>