<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26.12.2022
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>edit question</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/view/header.jsp"/>


<div class="offcanvas offcanvas-start" id="forAddQuestion">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title"><fmt:message key="button.edit.question"/></h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="edit_question">
            <input type="hidden" name="page" value="${requestScope.page}">
            <input type="hidden" name="test_id" value="${requestScope.test_id}">
            <input type="hidden" name="question_id" value="${question.id}">
            <h6 class="fw-light"><fmt:message key="table.head.text.of.question"/></h6>
            <input type="text" name="text" value="${requestScope.question.text}">
            <br>
            <div class="d-flex justify-content-center">
                <button type="submit"
                        class="btn btn-secondary"><fmt:message key="button.edit"/>
                </button>
            </div>
        </form>
    </div>
</div>
<!-- Button to open the offcanvas sidebar -->
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forAddQuestion">
    <fmt:message key="button.edit.question"/>
</button>
<%--==================================================--%>

<div class="offcanvas offcanvas-start" id="forAddAnswer">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title"><fmt:message key="button.add.answer"/></h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="add_answer">
            <input type="hidden" name="page" value="${requestScope.page}">
            <input type="hidden" name="test_id" value="${requestScope.test_id}">
            <input type="hidden" name="question_id" value="${requestScope.question.id}">
            <input type="text" required placeholder="<fmt:message key="table.head.text.of.answer"/>" name="text"
                   value="${requestScope.too_long_answer}"><br>
            <input type="radio" name="result" value="true"><fmt:message key="select.option.true"/> <br>
            <input type="radio" name="result" value="false"><fmt:message key="select.option.false"/> <br>
            <div class="d-flex justify-content-center">
                <button type="submit"
                        class="btn btn-secondary"><fmt:message key="button.add"/>
                </button>
            </div>
        </form>
    </div>
</div>
<!-- Button to open the offcanvas sidebar -->
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forAddAnswer">
    <fmt:message key="button.add.answer"/>
</button>
<%--====================================================--%>
<%--<div class="offcanvas offcanvas-start" id="forAddImage">--%>
<%--    <div class="offcanvas-header">--%>
<%--        <h1 class="offcanvas-title">EDIT IMAGE </h1>--%>
<%--        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>--%>
<%--    </div>--%>
<%--    <div class="offcanvas-body">--%>
<%--        <form method="post" action="upload_image" enctype="multipart/form-data">--%>
<%--            <input type="hidden" name="page" value="${requestScope.page}">--%>
<%--            <input type="hidden" name="test_id" value="${requestScope.test_id}">--%>
<%--            <input type="hidden" name="question_id" value="${requestScope.question.id}">--%>
<%--            <input type="file" name="file"/>--%>
<%--            <input type="submit" value="Upload"/>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forAddImage">--%>
<%--    EDIT IMAGE--%>
<%--</button>--%>

<%--=========================================--%>

<form action="edit_test">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>

<form action="filter_images">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="hidden" name="question_id" value="${requestScope.question.id}">
    <input class="btn btn-secondary" type="submit" value="to Filter Image">
</form>

<form method="post" action="remove_image">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input type="hidden" name="test_id" value="${requestScope.test_id}">
    <input type="hidden" name="question_id" value="${requestScope.question.id}">
    <input class="btn btn-secondary" type="submit" value="Remove Image">
</form>


<c:out value="${requestScope.message_answer}"/>
<c:out value="${requestScope.message}"/>


<c:if test="${requestScope.question.urlImage != null}">
    <img src="${requestScope.question.urlImage}" width="256" height="256" class="rounded mx-auto d-block" alt="...">
</c:if>

<div class="row d-flex justify-content-center align-items-center h-3">
    <div class="col-6 col-md-6 col-lg-6 col-xl-6">
        <div class="card" style="border-radius: 15px;">
            <div class="card-body p-4">
                <h5 class="text-center mb-0">${requestScope.question.text}</h5>
            </div>
        </div>
    </div>
</div>
<br>


<table class="table align-middle mb-0 w-auto bg-white">
    <thead class="bg-secondary">
    <tr>
        <th><fmt:message key="table.head.text.of.answer"/></th>
        <th><fmt:message key="table.head.result.answer"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="answer" items="${requestScope.answers}">
    <tr>
        <td><c:out value="${answer.text}"/></td>
        <td><c:out value="${answer.result}"/></td>
        <td>
            <form method="post" action="delete_answer">
                <input type="hidden" name="page" value="${requestScope.page}">
                <input type="hidden" name="test_id" value="${requestScope.test_id}">
                <input type="hidden" name="question_id" value="${requestScope.question.id}">
                <input type="hidden" name="answer_id" value="${answer.id}">
                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.delete"/>">
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>


</body>
</html>
