<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="account" scope="request" class="Model.Account"/>


<form method="post"
      action="/LArteDelMangiare_war_exploded/accounts/signup"
      class="app grid-x justify-center align-center">
    <fieldset class="grid-y cell w50 login">
        <c:if test="${not empty alert}">
            <%@include file="../partials/alert.jsp" %>
        </c:if>

        <h2>Register!</h2>

        <span>Username:</span>
        <label for="userName" class="field cell">
            <input id="userName" name="userName" placeholder="Username" type="text">
        </label>

        <span>Name:</span>
        <label for="firstName" class="field cell">
            <input id="firstName" name="firstName" placeholder="Name" type="text">
        </label>

        <span>Surname:</span>
        <label for="lastName" class="field cell">
            <input id="lastName" name="lastName" placeholder="Surname" type="text">
        </label>

        <span>Address:</span>
        <label for="address" class="field cell">
            <input id="address" name="address" placeholder="Address" type="text">
        </label>

        <span>Email:</span>
        <label for="email" class="field cell">
            <input id="email" name="email" placeholder="Email" type="email">
        </label>

        <span>Password:</span>
        <label for="password" class="cell field">
            <input type="password" id="password" placeholder="Password" name="password">
        </label>
        <button type="submit" class=" cell btn secondary">Register!</button>
    </fieldset>
</form>