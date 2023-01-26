<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>quiz</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>
<%--<form method="post" action="create_test">--%>
<%--    <input type="hidden" name="page" value="${requestScope.page}">--%>
<%--    <input type="text" required placeholder="<fmt:message key="table.head.test.name"/>" name="name"--%>
<%--           value="${requestScope.name}"><br>--%>
<%--    <input type="text" required placeholder="<fmt:message key="table.head.subject"/>" name="subject"--%>
<%--           value="${requestScope.subject}"><br>--%>
<%--    <input type="number" required placeholder="<fmt:message key="table.head.difficult"/>" name="difficult"--%>
<%--           value="${requestScope.difficult}"><br>--%>
<%--    <input type="number" required placeholder="<fmt:message key="table.head.duration"/>" name="duration"--%>
<%--           value="${requestScope.duration}"><br>--%>
<%--    <input class="button" type="submit" value="<fmt:message key="button.create.test"/>">--%>
<%--</form>--%>
<%--<c:out value="${requestScope.message}"/>--%>


<section class="vh-100 bg-image">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-1">
                <div class="col-4 col-md-5 col-lg-5 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">
                            <h2 class="text-uppercase text-center mb-0"><fmt:message key="button.create.test"/></h2>
                            <form method="post" action="create_test">
                                <div class="form-outline mb-0">
                                    <input type="text" class="form-control form-control-lg"
                                           required placeholder="<fmt:message key="table.head.test.name"/>"
                                           name="name"
                                           value="${requestScope.name}"/>
                                </div>
                                <div class="form-outline mb-0">
                                    <input type="text" class="form-control form-control-lg"
                                           required placeholder="<fmt:message key="table.head.subject"/>"
                                           name="subject"
                                           value="${requestScope.subject}"/>
                                </div>
                                <div class="form-outline mb-0">
                                    <input class="form-control form-control-lg" type="number" required
                                           placeholder="<fmt:message key="table.head.difficult"/>"
                                           name="difficult"
                                           value="${requestScope.difficult}"/>
                                </div>
                                <div class="form-outline mb-0">
                                    <input class="form-control form-control-lg"
                                           type="number" required placeholder="<fmt:message key="table.head.duration"/>"
                                           name="duration"
                                           value="${requestScope.duration}"/>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <button type="submit"
                                            class="btn btn-secondary">
                                        <fmt:message key="button.create.test"/></button>
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
