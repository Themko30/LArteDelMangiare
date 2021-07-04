<form class="app grid-x justify-center align-center"
      action="/LArteDelMangiare_war_exploded/accounts/signin"
      method="post">
    <fieldset class="grid-y cell w50 login">
        <c:if test="${not empty alert}">
            <%@include file="../partials/alert.jsp" %>
        </c:if>
        <h2>Login</h2>
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