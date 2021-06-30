<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="LADM-Home"/>
        <jsp:param name="styles" value="crm,dashboard"/>
        <jsp:param name="scripts" value="crm"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">

            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Registered Clients"/>
                <jsp:param name="stat" value="${sizeAccounts}"/>
            </jsp:include>

            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Products in Stock"/>
                <jsp:param name="stat" value="${sizeProducts}"/>
            </jsp:include>

            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Total Earnings"/>
                <jsp:param name="stat" value="${earnings} Euro"/>
            </jsp:include>

            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Total Orders"/>
                <jsp:param name="stat" value="${purchases}"/>
            </jsp:include>

        </div>
        <%@include file="../partials/crm/footer.jsp" %>
    </section>
</main>
</body>
</html>
