<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table products-table">
    <caption style="color: var(--primary)">Categories List</caption>
    <a style="text-decoration: none; color: var(--primary);"
       href="/LArteDelMangiare_war_exploded/categories/create">Create
        Category</a>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Image</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${categories.isEmpty()}">
            <tr>
                <td>No Categories</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td data-head="id">
                        <a href="/LArteDelMangiare_war_exploded/categories/show?id=${category.id}">${category.id}</a>
                    </td>
                    <td data-head="Name">${category.label}</td>
                    <td data-head="Description">${category.description}</td>
                    <td data-head="image">
                        <a href="/LArteDelMangiare_war_exploded/covers/${category.image}">Image</a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>