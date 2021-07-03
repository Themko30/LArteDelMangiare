<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="country" scope="request" class="Model.Country"/>
<c:set var="isCreate" value="${country.id == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp" %>
</c:if>

<form method="post"
      action="/LArteDelMangiare_war_exploded/countries/${isCreate ? 'create' : 'update'}">
    <c:if test="${not isCreate}">
        <input type="hidden" name="id" value="${country.id}">
    </c:if>
    <fieldset class="grid-y cell product-form">
        <legend>${isCreate ? 'Create' : 'Update'} Country</legend>

        <label for="label" class="field cell">
            <input id="label" name="label" placeholder="Name" type="text"
                   value="${country.label}">
        </label>

        <button type="submit" class=" cell btn primary">${isCreate ? 'Create' : 'Update'}</button>
    </fieldset>
</form>