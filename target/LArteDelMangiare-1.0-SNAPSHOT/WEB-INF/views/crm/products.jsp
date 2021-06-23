<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="LADM-Products Management"/>
        <jsp:param name="styles" value="crm,products"/>
        <jsp:param name="scripts" value="crm"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">
            <section class="grid-y cell products">
                <%@ include file="../product/table.jsp" %>
            </section>
        </div>
        <%@include file="../partials/crm/footer.jsp" %>
    </section>
</main>
</body>
</html>
