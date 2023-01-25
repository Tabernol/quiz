<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 23.12.2022
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>users</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<form action="home">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>
<table class="sortable">
    <thead>
    <tr>
        <th><fmt:message key="table.head.name"/></th>
        <th><fmt:message key="table.head.login"/></th>
        <th><fmt:message key="table.head.status"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.blocked}"/></td>


            <td>
                <form method="post" action="block">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input class="button" type="submit" value=" <fmt:message key="button.block"/>">
                </form>
            </td>
            <td>
                <form action="edit_user">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input class="button" type="submit" value=" <fmt:message key="button.edit.user"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<table class="table align-middle mb-0  bg-white">
    <thead class="bg-light">
    <tr>
        <th>Name</th>
        <th>Title</th>
        <th>Status</th>
        <th>Position</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <div class="d-flex align-items-center">
                <img
                        src="https://mdbootstrap.com/img/new/avatars/8.jpg"
                        alt=""
                        style="width: 45px; height: 45px"
                        class="rounded-circle"
                />
                <div class="ms-3">
                    <p class="fw-bold mb-1">John Doe</p>
                    <p class="text-muted mb-0">john.doe@gmail.com</p>
                </div>
            </div>
        </td>
        <td>
            <p class="fw-normal mb-1">Software engineer</p>
            <p class="text-muted mb-0">IT department</p>
        </td>
        <td>
            <span class="badge badge-success rounded-pill d-inline">Active</span>
        </td>
        <td>Senior</td>
        <td>
            <button type="button" class="btn btn-link btn-sm btn-rounded">
                Edit
            </button>
        </td>
    </tr>
    <tr>
        <td>
            <div class="d-flex align-items-center">
                <img
                        src="https://mdbootstrap.com/img/new/avatars/6.jpg"
                        class="rounded-circle"
                        alt=""
                        style="width: 45px; height: 45px"
                />
                <div class="ms-3">
                    <p class="fw-bold mb-1">Alex Ray</p>
                    <p class="text-muted mb-0">alex.ray@gmail.com</p>
                </div>
            </div>
        </td>
        <td>
            <p class="fw-normal mb-1">Consultant</p>
            <p class="text-muted mb-0">Finance</p>
        </td>
        <td>
        <span class="badge badge-primary rounded-pill d-inline"
        >Onboarding</span
        >
        </td>
        <td>Junior</td>
        <td>
            <button
                    type="button"
                    class="btn btn-link btn-rounded btn-sm fw-bold"
                    data-mdb-ripple-color="dark"
            >
                Edit
            </button>
        </td>
    </tr>
    <tr>
        <td>
            <div class="d-flex align-items-center">
                <img
                        src="https://mdbootstrap.com/img/new/avatars/7.jpg"
                        class="rounded-circle"
                        alt=""
                        style="width: 45px; height: 45px"
                />
                <div class="ms-3">
                    <p class="fw-bold mb-1">Kate Hunington</p>
                    <p class="text-muted mb-0">kate.hunington@gmail.com</p>
                </div>
            </div>
        </td>
        <td>
            <p class="fw-normal mb-1">Designer</p>
            <p class="text-muted mb-0">UI/UX</p>
        </td>
        <td>
            <span class="badge badge-warning rounded-pill d-inline">Awaiting</span>
        </td>
        <td>Senior</td>
        <td>
            <button
                    type="button"
                    class="btn btn-link btn-rounded btn-sm fw-bold"
                    data-mdb-ripple-color="dark"
            >
                Edit
            </button>
        </td>
    </tr>
    </tbody>
</table>


</body>
</html>
