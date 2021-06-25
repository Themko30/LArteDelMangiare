<header class="navbar grid-x">
    <div class="cell grid-x" style="justify-content: space-between;">
        <h1 class="title align-center">L`Arte Del Mangiare</h1>
        <div class="grid-inline align-center icons">
            <%@include file="../../../../ICONS/user.svg" %>
            <p style="padding: 5px">User</p>
            <span class="shopping-cart">
        <%@ include file="../../../../ICONS/shopping_cart.svg" %>
        <span class="badge">0</span>
    </span>
        </div>
    </div>
    <nav class="menu grid-x cell align-center">
        <a href="#" class="">Home</a>
        <a href="#">Products</a>
        <div class="dropdown">
            <button class="dropbtn">Categories
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
                <a href="#">Link 1</a>
                <a href="#">Link 2</a>
                <a href="#">Link 3</a>
                <a href="#">Link 4</a>
                <a href="#">Link 5</a>
                <a href="#">Link 6</a>
                <a href="#">Link 7</a>
            </div>
        </div>
        <a href="#">Info</a>
    </nav>
</header>
