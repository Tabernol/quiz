<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 20.12.2022
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Login</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <script>
        <%@include file="/static/js/general.js"%>
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>
<section class="vh-100 bg-image">
<div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-1">
            <div class="col-4 col-md-5 col-lg-5 col-xl-6">
                <div class="card" style="border-radius: 15px;">
                    <div class="card-body p-5">
                        <h2 class="text-uppercase text-center mb-0">Login</h2>
                        <form method="post" action="login">
                            <div class="form-outline mb-0">
<%--                                change text on email--%>
                                <input type="text" id="login" class="form-control form-control-lg"
                                       name="login"/>
                                <label class="form-label" for="login"><fmt:message key="label.login"/></label>
                            </div>
                            <div class="form-outline mb-0">
                                <input type="password" id="pass" class="form-control form-control-lg"
                                       name="password"/>
                                <label class="form-label" for="pass"><fmt:message
                                        key="label.password"/></label>
                            </div>
                            <input type="checkbox" onclick="showPassword()">Show Password
                            <div class="d-flex justify-content-center">
                                <button type="submit"
                                        class="btn btn-secondary" onclick="showMessage(${requestScope.message})">
                                    <fmt:message key="label.Login"/></button>
                            </div>

                                <c:out value="${requestScope.message}"/>


                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>


</body>
</html>
