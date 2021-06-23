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
    <style>
      .product-form > * {
        margin-bottom: 0.5rem;
      }
    </style>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">
            <form method="post" action="/LArteDelMangiare_war_exploded/products/create"
                  enctype="multipart/form-data">
                <fieldset class="grid-y cell product-form">
                    <legend>Create Product</legend>

                    <label for="fullName" class="field cell w50">
                        <input id="fullName" name="fullName" placeholder="Name" type="text">
                    </label>

                    <label for="price" class="field cell w50">
                        <input id="price" name="price" placeholder="Price" type="text">
                    </label>

                    <label for="quantity" class="field cell w50">
                        <input id="quantity" name="quantity" placeholder="Quantity" type="text">
                    </label>

                    <label for="catId" class="field cell w50">
                        <select name="catId" id="catId">
                            <option value="1">Gunkan</option>
                            <option value="2">Hosomaki</option>
                        </select>
                    </label>

                    <label for="couId" class="field cell w50">
                        <select name="couId" id="couId">
                            <option value="1">Japan</option>
                            <option value="2">China</option>
                        </select>
                    </label>

                    <label for="cover" class="field cell w50">
                        <input id="cover" name="cover" type="file">
                    </label>
                    <button type="submit" class=" cell w50 btn primary">Create</button>
                </fieldset>
            </form>

        </div>
        <%@include file="../partials/crm/footer.jsp" %>
    </section>
</main>
</body>
</html>
