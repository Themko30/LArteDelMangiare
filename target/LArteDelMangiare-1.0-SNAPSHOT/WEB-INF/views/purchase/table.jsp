<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table products-table">
    <caption style="color: var(--primary)">Purchases List</caption>
    <a style="text-decoration: none; color: var(--primary);"
       href="/LArteDelMangiare_war_exploded/purchases/create">Create
        Purchase</a>
    <thead>
    <tr>
        <th>Id</th>
        <th>Card Circuit</th>
        <th>Card Number</th>
        <th>Date</th>
        <th>Total</th>
        <th>Account Id</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${purchases.isEmpty()}">
            <tr>
                <td>No Purchases</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${purchases}" var="purchase">
                <tr>
                    <td data-head="id">
                        <a href="/LArteDelMangiare_war_exploded/purchases/show?id=${purchase.id}">${purchase.id}</a>
                    </td>
                    <td data-head="Card Circuit">${purchase.cardCircuit}</td>
                    <td data-head="Card Pan">${purchase.panCard}</td>
                    <td data-head="Date">${purchase.created}</td>
                    <td data-head="Total">${purchase.total}</td>
                    <td data-head="Account Id">
                        <a href="/LArteDelMangiare_war_exploded/accounts/show?id=${purchase.accountNum}">${purchase.accountNum}</a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>