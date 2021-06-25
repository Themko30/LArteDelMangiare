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
        <img src="/LArteDelMangiare_war_exploded/IMAGES/logo.png" width="150" height="150">
        <h1>Categories:</h1>
        <div class="grid-x">
            <img src="/LArteDelMangiare_war_exploded/IMAGES/Hosomaki.png" width="70" height="70"
                 alt="Hosomaki">
            <a href="#">Hosomaki</a>
            <img src="/LArteDelMangiare_war_exploded/IMAGES/uramaki.png" width="70" height="70"
                 alt="Uramaki">
            <p>Uramaki</p>
            <img src="/LArteDelMangiare_war_exploded/IMAGES/gunkan.png" width="70" height="70"
                 alt="Gunkan">
            <p>Gunkan</p>
            <img src="/LArteDelMangiare_war_exploded/IMAGES/temaki.png" width="70" height="70"
                 alt="Temaki">
            <p>Temaki</p>
            <img src="/LArteDelMangiare_war_exploded/IMAGES/onigiri.png" width="70" height="70"
                 alt="Onigiri">
            <p>Onigiri</p>
            <img src="/LArteDelMangiare_war_exploded/IMAGES/sashimi.png" width="70" height="70"
                 alt="Sashimi">
            <p>Sashimi</p>
            <img src="/LArteDelMangiare_war_exploded/IMAGES/nigiri.png" width="70" height="70"
                 alt="Nigiri">
            <p>Nigiri</p>
        </div>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>