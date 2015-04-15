
function toggleContact() {
   var cntctTbl = document.getElementById("ContactTable");
   cntctTbl.hidden = !cntctTbl.hidden;
   var cntctBtn = document.getElementById("ContactButton");
   if(cntctTbl.hidden)
      cntctBtn.innerHTML = "View";
   else
      cntctBtn.innerHTML = "Hide";
}

function toggleBookOut() {
   var bkOutTbl = document.getElementById("BookOutTable");
   bkOutTbl.hidden = !bkOutTbl.hidden;
   var bkOutBtn = document.getElementById("BookOutButton");
   if(bkOutTbl.hidden)
      bkOutBtn.innerHTML = "View";
   else
      bkOutBtn.innerHTML = "Hide";
}

function toggleBookRes() {
   var bkResTbl = document.getElementById("BookResTable");
   bkResTbl.hidden = !bkResTbl.hidden;
   var bkResBtn = document.getElementById("BookResButton");
   if(bkResTbl.hidden)
      bkResBtn.innerHTML = "View";
   else
      bkResBtn.innerHTML = "Hide";
}
