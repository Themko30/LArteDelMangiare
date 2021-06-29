<header class="topbar grid-x align-center">
    <%@include file="../../../../ICONS/menu.svg" %>
    <label class="field command">
        <input type="text" placeholder="Commands">
    </label>
    <span class="account">
        <%@include file="../../../../ICONS/user.svg" %>
        Welcome ${accountSession.username}
    </span>
</header>