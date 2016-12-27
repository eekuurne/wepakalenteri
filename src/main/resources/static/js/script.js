$(document).ready(function() {
  console.log("Javascript works!");

  initDatepicker();
});

function initDatepicker() {
  document.getElementById("startDate").value = getDateDaysFromNow(0); 
  document.getElementById("endDate").value = getDateDaysFromNow(120); 
}

function getDateDaysFromNow(days) {
  var today = new Date(new Date().getTime() + days * (24 * 60 * 60 * 1000));
  var dd = today.getDate();
  var mm = today.getMonth() + 1; //January is 0!
  var yyyy = today.getFullYear();

  if(dd<10) {
      dd = '0' + dd;
  } 
  if(mm<10) {
      mm = '0' + mm;
  } 
  return yyyy + '-' + mm + '-' + dd;
}
