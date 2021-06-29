<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="product" scope="request" class="Model.Product"/>
<c:set var="isCreate" value="${product.id == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp" %>
</c:if>

<form method="post"
      action="/LArteDelMangiare_war_exploded/products/${isCreate ? 'create' : 'update'}"
      enctype="multipart/form-data">
    <c:if test="${not isCreate}">
        <input type="hidden" name="id" value="${product.id}">
    </c:if>
    <fieldset class="grid-y cell product-form">
        <legend>${isCreate ? 'Create' : 'Update'} Product</legend>

        <label for="fullName" class="field cell">
            <input id="fullName" name="fullName" placeholder="Name" type="text"
                   value="${product.prodName}">
        </label>

        <label for="price" class="field cell">
            <input id="price" name="price" placeholder="Price" type="text" value="${product.price}">
        </label>

        <label for="quantity" class="field cell">
            <input id="quantity" name="quantity" placeholder="Quantity" type="text"
                   value="${product.quantity}">
        </label>

        <label for="catId" class="field cell">
            <select name="catId" id="catId">
                <option value="1">Hosomaki</option>
                <option value="2">Uramaki</option>
                <option value="3">Gunkan</option>
                <option value="4">Temaki</option>
                <option value="5">Onigiri</option>
                <option value="6">Nigiri</option>
                <option value="7">Sashimi</option>
            </select>
        </label>

        <label for="couId" class="field cell">
            <select name="couId" id="couId">
                <option value="1">Japan</option>
                <option value="2">South Korea</option>
                <option value="3">China</option>
                <option value="4">Eastern</option>
            </select>
        </label>

        <label for="description" class="field cell">
            <input id="description" name="description"
                   placeholder="Describe the product" type="text" value="${product.label}">
        </label>

        <label for="cover" class="field cell">
            <input id="cover" name="cover" type="file">
        </label>
        <button type="submit" class=" cell btn primary">${isCreate ? 'Create' : 'Update'}</button>
    </fieldset>
</form>