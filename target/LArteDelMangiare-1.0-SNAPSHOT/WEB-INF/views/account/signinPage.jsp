<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Welcome to L`Arte Del Mangiare"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site"/>
    </jsp:include>
    <style>
      .app {
        font-family: "Roboto Slab";
        font-style: normal;
        font-weight: normal;
      }

      .login {
        padding: 1rem;
        background-color: var(--primary);
        border-radius: 10px;
      }

      .login > * {
        margin: 10px;
      }
    </style>

</head>
<body>
<main class="app grid-y">
    <%@include file="../partials/site/header.jsp" %>
    <section class="body grid-x">
        <%@include file="signinform.jsp" %>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>