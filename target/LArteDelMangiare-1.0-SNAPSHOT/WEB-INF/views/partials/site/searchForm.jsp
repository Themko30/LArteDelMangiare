<form class="search grid-x justify-center align-center"
      action="/LArteDelMangiare_war_exploded/pages/search"
      method="get" id="form">
    <fieldset class="grid-y cell w50 login">
        <span>Name:</span>
        <label class="field" for="prodName">
            <input type="text" id="prodName" name="prodName" placeholder="Name">
        </label>
        <small class="errMsg cell"></small>
        <span>Category:</span>
        <label class="field" for="categoryId">
            <select name="categoryId" id="categoryId">
                <option disabled selected value> -- Select an option --</option>
            </select>
        </label>
        <small class="errMsg cell"></small>
        <span>Price:</span>
        <label class="field">
            Min:
            <input type="number" placeholder="From" name="minPrice">
            Max:
            <input type="number" placeholder="To" name="maxPrice">
        </label>
        <small class="errMsg cell"></small>
        <span>Nation:</span>
        <label class="field" for="countryId">
            <select name="countryId" id="countryId">
                <option disabled selected value> -- Select an option --</option>
            </select>
        </label>
        <small class="errMsg cell"></small>
        <button type="submit" class=" cell btn secondary">Search</button>
    </fieldset>
</form>