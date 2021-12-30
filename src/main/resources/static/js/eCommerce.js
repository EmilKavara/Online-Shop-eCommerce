let sidebarToggle= document.querySelector(".sidebarToggle");
sidebarToggle.addEventListener("click", function(){
  document.querySelector("body").classList.toggle("active");
  document.getElementById("sidebarToggle").classList.toggle("active");
});

var triggerTabList = [].slice.call(document.querySelectorAll('#categories a'))
triggerTabList.forEach(function (triggerEl) {
  var tabTrigger = new bootstrap.Tab(triggerEl)

  triggerEl.addEventListener('click', function (event) {
    event.preventDefault()
    tabTrigger.show()
  })
})


