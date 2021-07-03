<form class="search grid-x justify-center align-center"
      action="/LArteDelMangiare_war_exploded/pages/search"
      method="get">
    <fieldset class="grid-y cell w50 login">
        <span>Name:</span>
        <label class="field" for="prodName">
            <input type="text" id="prodName" name="prodName" placeholder="Name">
        </label>
        <span>Category:</span>
        <label class="field" for="categoryId">
            <select name="categoryId" id="categoryId">
                <option disabled selected value> -- Select an option --</option>
                <option value="1">Hosomaki</option>
                <option value="2">Uramaki</option>
                <option value="3">Gunkan</option>
                <option value="4">Temaki</option>
                <option value="5">Onigiri</option>
                <option value="6">Nigiri</option>
                <option value="7">Sashimi</option>
            </select>
        </label>
        <span>Price:</span>
        <label class="field">
            Min:
            <input type="number" placeholder="From" name="minPrice">
            Max:
            <input type="number" placeholder="To" name="maxPrice">
        </label>
        <span>Nation:</span>
        <label class="field" for="countryId">
            <select name="countryId" id="countryId">
                <option disabled selected value> -- Select an option --</option>
                <option value="1">Japan</option>
                <option value="2">South Korea</option>
                <option value="3">China</option>
                <option value="4">Eastern</option>
            </select>
        </label>
        <button type="submit" class=" cell btn secondary">Search</button>
    </fieldset>
</form>