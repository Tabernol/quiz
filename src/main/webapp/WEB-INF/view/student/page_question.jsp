<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 29.12.2022
  Time: 04:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:out value="${requestScope.text}"/>



<hr>


<form method="post" action="result_answer">
    <input type="hidden" name="id_question" value="${requestScope.id_question}">

    <c:forEach var="ans" items="${requestScope.answers}">
        <input  type="checkbox" name="res" value="${ans.result}">
<%--        <input type="checkbox" value="" ng-model="t.IsPullPoint" onclick="return false;"--%>
<%--               onkeydown="return false;"><span class="cr"></span></label>--%>
        <c:out value="${ans.text}"/>
        <br>
    </c:forEach>
    <input class="button" type="submit" value="Yes">
</form>

<c:forEach var="i" begin="1" end="${sessionScope.size}">
    <a href="<c:url value='/next_question'>

<%--    <c:param name="test_id" value="${requestScope.test_id}"/>--%>
    <c:param name="number_question" value="${i}"/>
</c:url>">
        <c:out value="${i}"/></a>

</c:forEach>
<hr>




</body>
</html>
