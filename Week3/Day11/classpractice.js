"use strict"

//document.body.innerHTML = "<p>My Web Page</p><p><a href='google.com'>go to google</a></p>"; 
//console.log("HERE");

let body=document.body;

let myP2=document.createElement('p');

let myP=document.createElement('p');
myP.id="p1";
myP.className="italic"; 
// for(let prop in myP){
//     console.log("prop: " + prop);
// }

let myHeader=document.createElement('h1');
myText=document.createTextNode("This is the header");

let myText=document.createTextNode("Here is text for the paragraph.");
myHeader.appendChild(myText);

myP.appendChild(myText);
myP2.appendChild(myText.cloneNode);
body.appendChild(myHeader);
body.appendChild(myP);
body.appendChild(myP2);

let pOfInterest=document.getElementsById("p2");
console.log("pOfInterest " + pOfInterest);
pOfInterest.style.fontWeight='bold'; 

let myDiv=document.createElement('div');
myDiv.style="border: 1px solid red; padding: 20px;";
body.appendChild(myDiv);

let myLink=document.createElement('a');
myLink.href="www.google.com";
myText=document.createTextNode("Go to Google");
myLink.appendChild(myText);
myDiv.appendChild(myLink);

let italicPars=document.getElementsByClassName("italic");
console.log("# of p in italic class is: " + italicPars.length);

for(let theP of italicPars){
    theP.style.fontStyle="italic";
}