<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1 style="color: white">Categories:</h1>
        <c:forEach items="${categories}" var="category">
            <div class="card" style="width: 10rem;">
                <img src="/LArteDelMangiare_war_exploded/covers/${category.image}"
                     class="card-img-top"
                     alt="...">
                <div class="card-body">
                    <h3 class="card-title">${category.label}</h3>
                    <p class="card-text">${category.description}</p>
                    <a class="btn" href="#">Go somewhere</a>
                </div>
            </div>
        </c:forEach>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>