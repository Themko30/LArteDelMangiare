<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Categories Management"/>
        <jsp:param name="styles" value="crm,products"/>
        <jsp:param name="scripts" value="crm"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body cell grid-x">
            <section class="grid-y cell">
                <%@ include file="../category/table.jsp" %>
                <jsp:include page="../partials/paginator.jsp">
                    <jsp:param name="resource" value="accounts"/>
                </jsp:include>
            </section>
        </div>
        <%@include file="../partials/crm/footer.jsp" %>
    </section>
</main>
</body>
</html>
