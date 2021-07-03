<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table products-table">
    <caption style="color: var(--primary)">Countries List</caption>
    <a style="text-decoration: none; color: var(--primary);"
       href="/LArteDelMangiare_war_exploded/countries/create">Create
        Country</a>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${countries.isEmpty()}">
            <tr>
                <td>No Countries</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${countries}" var="country">
                <tr>
                    <td data-head="id">
                        <a href="/LArteDelMangiare_war_exploded/countries/show?id=${country.id}">${country.id}</a>
                    </td>
                    <td data-head="Name">${country.label}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>