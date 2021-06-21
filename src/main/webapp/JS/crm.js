const hamburger = document.getElementsByClassName(
    "topbar")[0].firstElementChild;
hamburger.addEventListener("click", function () {
  const sidebar = document.getElementsByClassName("sidebar")[0];
  const content = document.getElementsByClassName("content")[0];
  sidebar.classList.toggle("collapse");
  content.classList.toggle('full-width');
})