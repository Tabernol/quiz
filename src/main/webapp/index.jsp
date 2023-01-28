<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>


<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="UTF-8">
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <title>Hello Quiz</title>

    <%--    <link rel="stylesheet" href="/static/css/style.css">--%>
    <style>
        <%@include file="/static/css/style.css"%>

    </style>

    <%--    <script type="text/javascript">--%>
    <%--        <%@include  file="/static/js/timer.js"%>--%>
    <%--    </script>--%>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"/>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"/>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
          rel="stylesheet"/>

    <!-- MDB -->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>
<h1 id="start_message"><fmt:message key="label.start_message"/></h1>
<br>

<%--<div class="row d-flex justify-content-center align-items-center h-3">--%>
<%--    <div class="col-6 col-md-6 col-lg-6 col-xl-6">--%>
<%--        <div class="card" style="border-radius: 15px;">--%>
<%--            <div class="card-body p-4">--%>
<%--                <h5 class="text-uppercase text-center mb-0">Al</h5>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
    <div class="progress-bar" style="width: 25%">25%</div>
</div>
</body>
</html>