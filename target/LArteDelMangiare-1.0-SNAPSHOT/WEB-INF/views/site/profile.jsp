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
    <c:if test="${not empty alert}">
        <%@include file="../partials/alert.jsp" %>
    </c:if>
    <section class="body grid-x justify-center">
        <div class="cell w50">
            <h2>Orders:</h2>
            <c:choose>
                <c:when test="${purchases.isEmpty()}">
                    <h3>There are no orders</h3>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${purchases}" var="purchase">
                        <details class="accordion">
                            <summary>Date: ${purchase.created} / Total: ${purchase.total}</summary>
                            <table class="table-accordion">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${purchase.cart.items}" var="item">
                                    <tr>
                                        <td data-head="Product">
                                            <a href="#">${item.product.prodName}</a>
                                        </td>
                                        <td data-head="Price">${item.product.price}</td>
                                        <td data-head="Quantity">${item.quantity}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </details>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>
