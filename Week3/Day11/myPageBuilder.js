"use strict"

let myBody = document.body;
myBody.style = "background-color: linen;";

let myHeader = document.createElement('h2');
myHeader.innerText = "Day11_HTML whiplash via JS <3";
myBody.appendChild(myHeader);

let mySection = document.createElement('section');

let myHeader3 = document.createElement('h3');
myHeader3.innerText = "links link, and the one below links to my github";
mySection.appendChild(myHeader3);
let myAnchor = document.createElement('a');
myAnchor.href = "https://github.com/polinaLyubavina";
myAnchor.innerText = "Click me to go to my GitHub";
mySection.appendChild(myAnchor);

myBody.appendChild(mySection);