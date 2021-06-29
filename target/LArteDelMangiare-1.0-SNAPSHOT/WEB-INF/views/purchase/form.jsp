<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="purchase" scope="request" class="Model.Purchase"/>
<c:set var="isCreate" value="${purchase.id == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp" %>
</c:if>

<form method="post"
      action="/LArteDelMangiare_war_exploded/purchases/${isCreate ? 'create' : 'update'}"
      enctype="multipart/form-data">
    <c:if test="${not isCreate}">
        <input type="hidden" name="id" value="${purchase.id}">
    </c:if>
    <fieldset class="grid-y cell product-form">
        <legend>${isCreate ? 'Create' : 'Update'} Purchase</legend>

        <label for="cardCircuit" class="field cell">
            <input id="cardCircuit" name="cardCircuit" placeholder="Card Circuit" type="text"
                   value="${purchase.cardCircuit}">
        </label>

        <label for="cardNumber" class="field cell">
            <input id="cardNumber" name="cardNumber" placeholder="Card Number"
                   type="text"
                   value="${purchase.panCard}">
        </label>

        <label for="Date" class="field cell">
            <input id="date" name="date" placeholder="Date" type="date" value="${purchase.created}">
        </label>

        <label for="total" class="field cell">
            <input id="total" name="total" placeholder="Total" type="text"
                   value="${purchase.total}">
        </label>

        <label for="accountId" class="field cell">
            <input id="accountId" name="accountId" placeholder="Account Id" type="text"
                   value="${purchase.accountNum}">
        </label>
        
        <button type="submit" class=" cell btn primary">${isCreate ? 'Create' : 'Update'}</button>
    </fieldset>
</form>