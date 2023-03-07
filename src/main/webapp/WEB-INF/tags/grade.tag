<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<%@ attribute name="grade" required="true" type="java.lang.Integer" description="Example attribute" %>

<c:choose>
    <c:when test="${grade == 100 }">
        <fmt:message key="grade.best"/> <c:out value="${grade}"/>
    </c:when>
    <c:when test="${ grade  < 60 }">
        <fmt:message key="grade.not.high"/> <c:out value="${grade}"/>
    </c:when>
    <c:when test="${grade > 70 }">
        <fmt:message key="grade.high"/> <c:out value="${grade}"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="grade.other"/> <c:out value="${grade}"/>
    </c:otherwise>
</c:choose>