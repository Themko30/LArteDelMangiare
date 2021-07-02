<%@ page import="Model.Product" %>
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
        <h1 style="color: white">Search Products:</h1>
        <c:forEach items="${products}" var="product">
            <div class="card" style="width: 10rem;">
                <img src="/LArteDelMangiare_war_exploded/covers/${product.image}"
                     class="card-img-top"
                     alt="NO IMAGE">
                <div class="card-body">
                    <h3 class="card-title">${product.prodName}</h3>
                    <p class="card-text">${product.price}€</p>
                    <p class="card-text">${product.label}</p>
                    <p class="card-text">${product.category.label}</p>
                    <p class="card-text">${product.country.label}</p>
                    <a class="btn" href="#">Add To Cart!</a>
                </div>
            </div>
        </c:forEach>
        <section class="grid-y cell">
            <jsp:include page="../partials/paginator.jsp">
                <jsp:param name="resource" value="search"/>
            </jsp:include>
        </section>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>