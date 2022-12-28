<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26.12.2022
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Language</title>
</head>
<body>
<div class="form">
  <form method="post" action="language">
    <select name="lang">
      <option value="en"><c:out value="english"/></option>
      <option value="ua"><c:out value="ukraine"/></option>
    </select><br>
    <input class="button" type="submit" value="choose">
  </form>
</div>
</body>
</html>
