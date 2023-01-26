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
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<%--<div class="form">--%>
<%--    <form method="post" action="registration">--%>
<%--        <input type="text" required placeholder="<fmt:message key="label.name"/>" name="name"><br>--%>
<%--        <input type="text" required placeholder="<fmt:message key="label.login"/>" name="login"><br>--%>
<%--        <input type="password" required placeholder="<fmt:message key="label.password"/>" name="password"><br>--%>
<%--        <input class="button" type="submit" value="<fmt:message key="label.Registration"/>">--%>
<%--    </form>--%>
<%--</div>--%>
<c:out value="${requestScope.message}"/>
<section class="vh-100 bg-image"
<%--         style="background-image: url('https://www.thesun.ie/wp-content/uploads/sites/3/2019/06/NINTCHDBPICT000496668972.jpg?strip=all&quality=100&w=1620&h=1080&crop=1');">--%>
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-1">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">
                            <h2 class="text-uppercase text-center mb-0">Create an account</h2>
                            <form>
                                <div class="form-outline mb-0">
                                    <input type="text" id="form3Example1cg" class="form-control form-control-lg" />
                                    <label class="form-label" for="form3Example1cg">Your Name</label>
                                </div>

                                <div class="form-outline mb-0">
                                    <input type="email" id="form3Example3cg" class="form-control form-control-lg" />
                                    <label class="form-label" for="form3Example3cg">Your Email</label>
                                </div>

                                <div class="form-outline mb-0">
                                    <input type="password" id="form3Example4cg" class="form-control form-control-lg" />
                                    <label class="form-label" for="form3Example4cg">Password</label>
                                </div>

                                <div class="form-outline mb-0">
                                    <input type="password" id="form3Example4cdg" class="form-control form-control-lg" />
                                    <label class="form-label" for="form3Example4cdg">Repeat your password</label>
                                </div>

<%--                                <div class="form-check d-flex justify-content-center mb-5">--%>
<%--                                    <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3cg" />--%>
<%--                                    <label class="form-check-label" for="form2Example3g">--%>
<%--                                        I agree all statements in <a href="#!" class="text-body"><u>Terms of service</u></a>--%>
<%--                                    </label>--%>
<%--                                </div>--%>

                                <div class="d-flex justify-content-center">
                                    <button type="button"
                                            class="btn btn-secondary">Register</button>
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
