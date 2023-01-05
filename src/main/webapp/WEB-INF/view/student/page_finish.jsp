<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 01.01.2023
  Time: 01:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Finish</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/language.jsp"/>

<h2> You result <c:out value="${requestScope.percent_result}"/> %</h2>


<hr>
<form action="home">
    <input class="button" type="submit" value="Home">
</form>

</body>
</html>
