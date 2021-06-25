<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table products-table">
    <caption style="color: var(--primary)">Products List</caption>
    <a style="text-decoration: none; color: black"
       href="/LArteDelMangiare_war_exploded/products/create">Crea
        Prodotto</a>
    <thead>
    <tr>
        <th>Id</th>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Description</th>
        <th>Image</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${products.isEmpty()}">
            <tr>
                <td>No Products</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td data-head="id">
                        <a href="#">${product.id}</a>
                    </td>
                    <td data-head="Name">${product.prodName}</td>
                    <td data-head="Quantity">${product.quantity}</td>
                    <td data-head="Price">${product.price}</td>
                    <td data-head="Description">${product.label}</td>
                    <td data-head="Url">
                        <a href="/LArteDelMangiare_war_exploded/covers/${product.image}">Image</a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>