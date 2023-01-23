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
</head>
<body>

<div id="text_question"></div>

<c:out value="${requestScope.text}"/>

<hr>
<form method="post" action="result_answer">
    <input id="id_question" type="hidden" name="id_question" value="${requestScope.id_question}">
    <input type="hidden" name="number_question" value="${requestScope.number_question}">
    <c:forEach var="ans" items="${requestScope.answers}">
        <input type="hidden" name="res" value="${ans.id}">
        <input type="checkbox" name="res">
        <c:out value="${ans.text}"/>
        <br>
    </c:forEach>

    <input class="button" type="submit" value="<fmt:message key="select.option.yes"/>">
    <button  type="button" onclick="loadDoc(document.getElementById('id_question').value)">Post Ajax</button>
</form>
<hr>

<script>
    function loadDoc(id_question) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("text_question").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "get_text_question?id_question=" + id_question, true);
        xhttp.send();
    }
</script>

</body>
</html>
