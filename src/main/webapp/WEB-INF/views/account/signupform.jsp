<form class="app grid-x justify-center align-center" action="${context}/accounts/secret"
      method="post">
    <fieldset class="grid-y cell w50 login">
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