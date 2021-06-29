<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="account" scope="request" class="Model.Account"/>
<c:set var="isCreate" value="${account.id == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp" %>
</c:if>

<form method="post"
      action="/LArteDelMangiare_war_exploded/accounts/${isCreate ? 'create' : 'update'}">
    <c:if test="${not isCreate}">
        <input type="hidden" name="id" value="${account.id}">
    </c:if>
    <fieldset class="grid-y cell product-form">
        <legend>${isCreate ? 'Create' : 'Update'} Account</legend>

        <label for="userName" class="field cell">
            <input id="userName" name="userName" placeholder="User Name" type="text"
                   value="${account.username}">
        </label>

        <label for="firstName" class="field cell">
            <input id="firstName" name="firstName" placeholder="Name" type="text"
                   value="${account.firstName}">
        </label>

        <label for="lastName" class="field cell">
            <input id="lastName" name="lastName" placeholder="Surname" type="text"
                   value="${account.lastName}">
        </label>

        <label for="address" class="field cell">
            <input id="address" name="address" placeholder="Address" type="text"
                   value="${account.address}">
        </label>

        <label for="email" class="field cell">
            <input id="email" name="email" placeholder="Email" type="email"
                   value="${account.email}">
        </label>

        <c:if test="${isCreate}">
            <label for="password" class="cell field">
                <input type="password" id="password" placeholder="Password" name="password"
                       value="${account.password}">
            </label>
        </c:if>

        <label for="admin" class="field cell">
            Is Admin?
            <select name="admin" id="admin">
                <option value="1">Yes</option>
                <option value="0">No</option>
            </select>
        </label>

        <button type="submit" class=" cell btn primary">${isCreate ? 'Create' : 'Update'}</button>
    </fieldset>
</form>