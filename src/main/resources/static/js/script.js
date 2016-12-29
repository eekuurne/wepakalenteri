$(document).ready(function() {
  if (window.location.pathname === "/calendar") {
    document.getElementById("calendar-nav").className = "active";
    initDatepicker();
  } else if (window.location.pathname === "/profile") {
    document.getElementById("profile-nav").className = "active";
  } else if (window.location.pathname.includes("/events/create")) {
    document.getElementById("new-event-nav").className = "active";
  }
});

function initDatepicker() {
  var pathVariables = getJsonFromUrl();

  if (pathVariables.startDate !== undefined) {
    document.getElementById("startDate").value = pathVariables.startDate;
  } else {
    document.getElementById("startDate").value = getDateDaysFromNow(0); 
  }
  if (pathVariables.endDate !== undefined) {
    document.getElementById("endDate").value = pathVariables.endDate;
  } else {
    document.getElementById("endDate").value = getDateDaysFromNow(120); 
  }
}

function getDateDaysFromNow(days) {
  var today = new Date(new Date().getTime() + days * (24 * 60 * 60 * 1000));
  var dd = today.getDate();
  var mm = today.getMonth() + 1;
  var yyyy = today.getFullYear();

  if(dd<10) {
    dd = '0' + dd;
  } 
  if(mm<10) {
    mm = '0' + mm;
  } 
  return yyyy + '-' + mm + '-' + dd;
}

function getJsonFromUrl() {
  var query = location.search.substr(1);
  var result = {};
  query.split("&").forEach(function(part) {
    var item = part.split("=");
    result[item[0]] = decodeURIComponent(item[1]);
  });
  return result;
}
