<header class="navbar grid-x">
    <div class="cell grid-x" style="justify-content: space-between;">
        <h1 class="title align-center">L`Arte Del Mangiare</h1>
        <div class="grid-inline align-center icons">
            <%@include file="../../../../ICONS/user.svg" %>
            <c:choose>
                <c:when test="${accountSession.username != null}">
                    <p style="padding: 5px">Welcome ${accountSession.username}</p>
                </c:when>
                <c:otherwise>
                    <p style="padding: 5px">Welcome Unregistered User!</p>
                </c:otherwise>
            </c:choose>
            <span class="shopping-cart">
        <%@ include file="../../../../ICONS/shopping_cart.svg" %>
        <span class="badge">0</span>
    </span>
        </div>
    </div>
    <nav class="menu grid-x cell align-center">
        <a href="/LArteDelMangiare_war_exploded/pages/home?page=1">Home</a>
        <a href="/LArteDelMangiare_war_exploded/pages/products?page=1">All Products</a>
        <div class="dropdown">
            <button class="dropbtn" style="color: white">Categories
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
                <c:forEach items="${categories}" var="category">
                    <a href="/LArteDelMangiare_war_exploded/pages/category?page=1&catId=${category.id}">${category.label}</a>
                </c:forEach>
            </div>
        </div>
        <a href="/LArteDelMangiare_war_exploded/pages/search">Search Products</a>
        <a href="/LArteDelMangiare_war_exploded/accounts/signin">TEST SIGNIN</a>
    </nav>
</header>
