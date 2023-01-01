<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.12.2022
  Time: 00:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Tests</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/language.jsp"/>
<div>
    <form action="filter_tests">
        <select name="sub">
            <option value="all"><c:out value="all"/></option>
            <c:forEach var="sub" items="${sessionScope.subjects}">
                <option value="${sub}"><c:out value="${sub}"/></option>
            </c:forEach>
        </select>
        <select name="order">
            <c:forEach var="order" items="${sessionScope.orders}">
                <option value="${order}"><c:out value="${order}"/></option>
            </c:forEach>
        </select>
        <select name="rows">
            <option value="2"><c:out value="2"/></option>
            <option value="5"><c:out value="5"/></option>
            <option value="10"><c:out value="10"/></option>
            <option value="20"><c:out value="20"/></option>
        </select><br>
        <input class="button" type="submit" value="filter">
    </form>

    <hr>
    <form action="home">
        <input class="button" type="submit" value="Home">
    </form>
    <table class="sortable">
        <thead>
        <tr>
            <th>Test name</th>
            <th>Subject</th>
            <th>Difficult</th>
            <th>Duration</th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="test" items="${sessionScope.tests}">
            <tr>
                <td><c:out value="${test.name}"/></td>
                <td><c:out value="${test.subject}"/></td>
                <td><c:out value="${test.difficult}"/></td>
                <td><c:out value="${test.duration}"/></td>

                <td>
                    <form action="edit_test">
                        <input type="hidden" name="test_id" value="${test.id}">
                        <input type="hidden" name="page" value="${requestScope.page}">
                        <input class="button" type="submit" value="edit test">
                    </form>

                </td>
                <td>
                    <form method="post" action="delete_test">
                        <input type="hidden" name="page" value="${requestScope.page}">
                        <input type="hidden" name="test_id" value="${test.id}">
                        <input class="button" type="submit" value="delete test">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:forEach var="i" begin="1" end="${requestScope.count_pages}">
        <a href="<c:url value='/next_page'>
    <c:param name="order" value="${requestScope.order}"/>
    <c:param name="sub" value="${requestScope.sub}"/>
    <c:param name="rows" value="${requestScope.rows}"/>
    <c:param name="page" value="${i}"/>
</c:url>">
            <c:out value="${i}"/></a>

    </c:forEach>
    <hr>

</div>
<jsp:include page="/WEB-INF/view/admin/create_test.jsp"/>

</body>
</html>
