<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Welcome to L`Arte Del Mangiare"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site"/>
    </jsp:include>
</head>
<body>
<main class="app grid-y">
    <%@include file="../partials/site/header.jsp" %>
    <section class="body grid-x">
        <img src="/LArteDelMangiare_war_exploded/IMAGES/logo.png" width="100" height="100">
        <h1>Categories</h1>
        <div class="grid-x">
            <img src="/LArteDelMangiare_war_exploded/IMAGES/Hosomaki.png" width="100" height="100"
                 alt="Hosomaki">
            <img src="" alt="Uramaki">
            <img src="" alt="Gunkan">
            <img src="" alt="Temaki">
            <img src="" alt="Onigiri">
            <img src="" alt="Nigiri">
            <img src="" alt="Sashimi">
        </div>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>