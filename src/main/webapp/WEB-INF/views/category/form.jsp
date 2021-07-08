<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="category" scope="request" class="Model.Category"/>
<c:set var="isCreate" value="${category.id == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp" %>
</c:if>

<form method="post"
      action="/LArteDelMangiare_war_exploded/categories/${isCreate ? 'create' : 'update'}"
      enctype="multipart/form-data" id="form">
    <c:if test="${not isCreate}">
        <input type="hidden" name="id" value="${category.id}">
    </c:if>
    <fieldset class="grid-y cell product-form">
        <legend>${isCreate ? 'Create' : 'Update'} Category</legend>

        <label for="label" class="field cell">
            <input id="label" name="label" placeholder="Label" type="text"
                   value="${category.label}">
        </label>
        <small class="errMsg cell"></small>

        <label for="description" class="field cell">
            <input id="description" name="description" placeholder="Category Description"
                   type="text"
                   value="${category.description}">
        </label>
        <small class="errMsg cell"></small>

        <label for="cover" class="field cell">
            <input id="cover" name="cover" type="file">
        </label>
        <small class="errMsg cell"></small>
        <button type="submit" class=" cell btn primary">${isCreate ? 'Create' : 'Update'}</button>
    </fieldset>
</form>