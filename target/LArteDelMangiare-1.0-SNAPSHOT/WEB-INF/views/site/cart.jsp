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
        <c:if test="${empty accountCart or accountCart.items.isEmpty()}">
            <h2 class="cell">Your cart is empty</h2>
        </c:if>
        <%--<c:otherwise>
            <h1 class="cell">Your Cart - Total: ${accountCart.total()}</h1>
            <table class="table cell">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${accountCart.items}" var="cartEntry">
                    <tr>
                        <td data-head="Name">${cartEntry.product.prodName}</td>
                        <td data-head="Price">${cartEntry.product.price}</td>
                        <td data-head="Quantity">${cartEntry.quantity}</td>
                        <td data-head="Actions">
                            <form method="post" action="/LArteDelMangiare_war_exploded/carts/remove"
                                  class="btn">
                                <input type="hidden" name="id" value="${cartEntry.product.id}">
                                <button class="btn primary" type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form method="post" action="/LArteDelMangiare_war_exploded/purchases/createClient"
                  class="cell">
                <button type="submit" class="primary btn">Complete Order</button>
            </form>
        </c:otherwise>--%>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>