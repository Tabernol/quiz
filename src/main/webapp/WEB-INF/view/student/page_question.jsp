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


<%--<button type="button"--%>
<%--        onclick="loadDoc(--%>
<%--                document.getElementById('id_question').value,--%>
<%--                document.getElementById('number_question').value,--%>
<%--                document.getElementsByName('res'))">Post Ajax--%>
<%--</button>--%>

<script>
    window.onload = function () {
        loadDoc(document.getElementById('id_question').value,
            document.getElementById('number_question').value,
            document.getElementsByName('res'));

        timer(${requestScope.duration})
    }


    let isTimerRun = true;

    function call_me(params) {
        let result = "";
        for (i = 0; i < params.length; i++) {
            result += "&res=" + params[i].value
            if (params[i].checked) {
                result += "&res=on"
                alert("res " + params[i].value + " on")
            } else {
                result += "&res=off"
                alert("res " + params[i].value + " off")
            }
        }
        return result;
    }


    function loadDoc(id_question, number_question, res) {
        console.log("size quiz " + ${sessionScope.size} +" number  " + number_question)
        console.log("is run " + isTimerRun);
        if ((${sessionScope.size}).toString() === number_question) {
            console.log("IFFFF");
            isTimerRun = false;
        }
        let result = call_me(res)
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("text_question").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "get_text_question?id_question=" + id_question +
            "&number_question=" + number_question +
            result, true);
        xhttp.send();
    }

    function timer(min) {
        var minute = --min;
        var sec = 59;
        setInterval(function () {
            document.getElementById("timer").innerHTML = minute + ":" + sec;
            if (isTimerRun) {
                sec--;
                if (sec == 00) {
                    minute--;
                    sec = 60;

                    if (minute == 0) {
                        //finish and result
                    }
                }
            }
        }, 1000);
    }
</script>

</body>
</html>
