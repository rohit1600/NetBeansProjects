// Timestamp of cart that page was last updated with
var lastCartUpdate = 0;

/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function addToCart(itemCode) {

 var req = newXMLHttpRequest();

 req.onreadystatechange = getReadyStateHandler(req, updateCart);
 
 req.open("POST", "cart.do", true);
 req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 req.send("action=add&item="+itemCode);
}
function removeFromCart(itemCode) {

 var req = newXMLHttpRequest();

 req.onreadystatechange = getReadyStateHandler(req, updateCart);
 
 req.open("POST", "cart.do", true);
 req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 req.send("action=remove&item="+itemCode);
}   


/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
function updateCart(cartJSON) {
 var cartObj = jsonParse(cartJSON);
 alert (cartJSON);
 /*
 var generated = cart.generated;
 if (generated > lastCartUpdate) {
   lastCartUpdate = generated;
   var contents = document.getElementById("contents");
   contents.innerHTML = "";

   var items = cart.item;
   for (var I = 0 ; I < items.length ; I++) {

     var item = cart.item[I];
     var name = cart.item[I].name;
     var quantity = cart.item[I].quantity;

     var listItem = document.createElement("li");
     listItem.appendChild(document.createTextNode(name+" x "+quantity));
     contents.appendChild(listItem);
   }

 }

 document.getElementById("total").innerHTML = cart.getAttribute("total");
 */
}
