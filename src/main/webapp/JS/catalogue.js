ajax({
  url: '/LArteDelMangiare_war_exploded/categories/api',
  method: 'GET',
  success: function (data) {
    const select = document.getElementById("categoryId");
    for (let index in data.categories) {
      let option = document.createElement("option");
      let optionText = document.createTextNode(data.categories[index].label);
      option.setAttribute("value", data.categories[index].id);
      option.appendChild(optionText);
      select.appendChild(option);
    }
  },
  error: function (err) {
    console.log(err);
  }
});

ajax({
  url: "/LArteDelMangiare_war_exploded/countries/api",
  method: 'GET',
  success: function (data) {
    const select = document.getElementById("countryId");
    for (let index in data.countries) {
      let option = document.createElement("option");
      let optionText = document.createTextNode(data.countries[index].label);
      option.setAttribute("value", data.countries[index].id);
      option.appendChild(optionText);
      select.appendChild(option);
    }
  },
  error: function (err) {
    console.log(err);
  }
});