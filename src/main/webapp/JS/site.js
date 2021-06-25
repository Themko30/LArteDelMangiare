/*const dropdown = document.getElementsByClassName("dropdown")[0];
dropdown.addEventListener('click', function () {
  const content = document.getElementsByClassName("dropdown-menu")[0];
  content.classList.toggle('full-width');
})*/

/*const homeImg = document.getElementsByClassName('body')[0].firstElementChild;
homeImg.addEventListener("click", function () {
  window.location.href = '/LArteDelMangiare_war_exploded/pages/';
})*/

const homeText = document.getElementsByClassName(
    "navbar")[0].getElementsByTagName("h1")[0]
homeText.addEventListener("click", function () {
  window.location.href = '/LArteDelMangiare_war_exploded/pages/';
})
