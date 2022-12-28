<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26.12.2022
  Time: 02:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit user</title>
</head>
<body>
<form method="post" action="edit_user">
    <input type="hidden" name="user_id" value="${requestScope.user.id}">
    Name <input type="text" name="name" value="${requestScope.user.name}"><br>
    Login <input type="text" name="login" value="${requestScope.user.login}"><br>
    Password <input type="text" name="password" value="${requestScope.user.password}"><br>
    Role <select name="role">
    <option value="student"> student</option>
    <option value="admin"> admin</option>
</select><br>
    Status <select name="status">
    <option value="false"> false</option>
    <option value="true"> true</option>
</select>
    <br>

    <%--    <input type="text" name="status" value="${requestScope.user.blocked}"><br>--%>
    <input class="button" type="submit" value="Edit user">
</form>

</body>
</html>
