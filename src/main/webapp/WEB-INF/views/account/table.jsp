<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table products-table">
    <caption style="color: var(--primary)">Accounts List</caption>
    <a style="text-decoration: none; color: var(--primary);"
       href="/LArteDelMangiare_war_exploded/accounts/create">Create
        Account</a>
    <thead>
    <tr>
        <th>Id</th>
        <th>User Name</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Address</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${accounts.isEmpty()}">
            <tr>
                <td>No Products</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td data-head="id">
                        <a href="/LArteDelMangiare_war_exploded/account/show?=${account.id}">${account.id}</a>
                    </td>
                    <td data-head="User Name">${account.username}</td>
                    <td data-head="First Name">${account.firstName}</td>
                    <td data-head="Last Name">${account.lastName}</td>
                    <td data-head="Address">${account.address}</td>
                    <td data-head="Email">${account.email}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>