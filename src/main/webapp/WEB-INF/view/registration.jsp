<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 20.12.2022
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Registration</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <script>
        <%@include file="/static/js/general.js"%>
    </script>
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<c:if test="${requestScope.message_bad_request != null}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> <c:out value="${requestScope.message_bad_request}"/>
    </div>
</c:if>

<section class="vh-100 bg-image">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-1">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">
                            <h2 class="text-uppercase text-center mb-0"><fmt:message key="head.create.account"/></h2>
                            <form method="post" action="registration">
                                <div class="form-outline mb-0">
                                    <input type="text" id="form1" class="form-control form-control-lg" name="name"
                                           value="${requestScope.repeat_name}"/>
                                    <label class="form-label" for="form1"><fmt:message key="your.name"/></label>
                                </div>

                                <div class="form-outline mb-0">
                                    <input type="email" id="form3" class="form-control form-control-lg" name="login"
                                           value="${requestScope.repeat_login}"/>
                                    <label class="form-label" for="form3"><fmt:message key="your.email"/></label>
                                </div>

                                <div class="form-outline mb-0">
                                    <input type="password" id="pass" class="form-control form-control-lg"
                                           name="password"/>
                                    <label class="form-label" for="pass"><fmt:message key="label.password"/></label>
                                </div>

                                <div class="form-outline mb-0">
                                    <input type="password" id="form5" class="form-control form-control-lg"
                                           name="repeat_password"/>
                                    <label class="form-label" for="form5"><fmt:message key="repeat.password"/></label>
                                </div>

                                <input type="checkbox" onclick="showPassword()"><fmt:message key="show.password"/>

                                <%--                                <div class="form-check d-flex justify-content-center mb-5">--%>
                                <%--                                    <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3cg" />--%>
                                <%--                                    <label class="form-check-label" for="form2Example3g">--%>
                                <%--                                        I agree all statements in <a href="#!" class="text-body"><u>Terms of service</u></a>--%>
                                <%--                                    </label>--%>
                                <%--                                </div>--%>
                                <div class="d-flex justify-content-center">
                                    <div class="g-recaptcha"
                                         data-sitekey="6LcpCHEkAAAAAD39lvkIlgR8GR53qSOtfv3_dZP0"></div>
                                </div>

                                <div class="d-flex justify-content-center">
                                    <button type="submit"
                                            class="btn btn-secondary"><fmt:message key="label.Registration"/>
                                    </button>
                                </div>
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
