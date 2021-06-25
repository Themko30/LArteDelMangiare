<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" dir="ltr">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login Admin"/>
    </jsp:include>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <style>
      .app {
        font-family: "Roboto Slab";
        font-style: normal;
        font-weight: normal;
        background: linear-gradient(var(--primary), var(--secondary));
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
<form class="app grid-x justify-center align-center" action="${context}/crm/secret"
      method="post">
    <fieldset class="grid-y cell w50 login">
        <h2>Login Admin Panel</h2>
        <span>Email</span>
        <label for="email" class="field">
            <input type="email" name="email" id="email" placeholder="Email">
        </label>
        <span>Password</span>
        <label for="password" class="field">
            <input type="password" name="password" id="password" placeholder="Password">
        </label>
        <button class="btn secondary" type="submit">Login</button>
    </fieldset>
</form>
</body>
</html>
