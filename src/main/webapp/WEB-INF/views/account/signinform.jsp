<form class="app grid-x justify-center align-center"
      action="/LArteDelMangiare_war_exploded/accounts/signin"
      method="post" id="form">
    <fieldset class="grid-y cell w50 login">
        <c:if test="${not empty alert}">
            <%@include file="../partials/alert.jsp" %>
        </c:if>
        <h2>Login</h2>
        <ol class="cell">
            <li>Email and Password must be compiled.</li>
            <li>Password Must contain at least one number and one uppercase and lowercase letter,
                and at least 8 and max 16 characters
            </li>
        </ol>
        <span>Email</span>
        <label for="email" class="field">
            <input type="email" name="email" id="email" placeholder="Email" required>
        </label>
        <small class="errMsg cell"></small>
        <span>Password</span>
        <label for="password" class="field">
            <input type="password" name="password" id="password" placeholder="Password" required
                   minlength="8" maxlength="16">
        </label>
        <small class="errMsg cell"></small>
        <button class="btn secondary" type="submit">Login</button>
    </fieldset>
</form>