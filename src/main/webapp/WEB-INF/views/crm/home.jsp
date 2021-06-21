<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="LADM-Home"/>
        <jsp:param name="styles" value="crm,dashboard"/>
        <jsp:param name="scripts" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <aside class="sidebar">
        <nav class="menu grid-y align-right">
            <img src="/LArteDelMangiare_war_exploded/IMAGES/logo.png" width="100" height="100">
            <a href="#">Client Management</a>
            <a href="#">Products Management</a>
            <a href="#">Orders Management</a>
            <a href="#">Categories Management</a>
            <a href="#">Countries Management</a>
            <a href="#">Profile</a>
            <a href="#">Logout</a>
        </nav>
    </aside>
    <section class="content grid-y">
        <header class="topbar grid-x align-center">
            <%@include file="../../../ICONS/menu.svg" %>
            <label class="field command">
                <input type="text" placeholder="Commands">
            </label>
            <span class="account">
                 <%@include file="../../../ICONS/user.svg" %>
                 Welcome Admin
             </span>
        </header>
        <div class="body">
            <div class="grid-y cell w40">
                <h2>Registered Clients</h2>
                <h4>87</h4>
            </div>
            <div class="grid-y cell w40">
                <h2>Products in Stock</h2>
                <h4>54</h4>
            </div>
            <div class="grid-y cell w40">
                <h2>Monthly Earnings</h2>
                <h4>1500 Euro</h4>
            </div>
            <div class="grid-y cell w40">
                <h2>Montlhy Orders</h2>
                <h4>89</h4>
            </div>
        </div>
        <footer class="info">
            <p>Copyright @2021. L`Arte Del Mangiare all rights reserved.</p>
        </footer>
    </section>
</main>
</body>
</html>
