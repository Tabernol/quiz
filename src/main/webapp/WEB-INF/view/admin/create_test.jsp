<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>quiz</title>
</head>
<body>
<form method="post" action="create_test">
  <input type="text" required placeholder="name" name="name"><br>
  <input type="text" required placeholder="subject" name="subject"><br>
  <input type="number" required placeholder="difficult" name="difficult"><br>
  <input type="number" required placeholder="duration" name="duration"><br>
  <input class="button" type="submit" value="Create test">
</form>

</body>
</html>
