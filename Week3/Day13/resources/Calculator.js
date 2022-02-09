"use strict"

let xTA = document.getElementById('xTA');
let yTA = document.getElementById('yTA');
let resultTA = document.getElementById('resultTA');
let wsTA = document.getElementById('wsTA');

// console.log(xTA);
// xTA.value = "got x";

// console.log(yTA);
// yTA.value = "got y";

// console.log(resultTA);
// resultTA.value = "got result";



//callback function that will handle a keypress 
function handleKeyPressCB(event){
    if(event.type == "click" || event.keyCode == 13){
        let x = Number(xTA.value);
        let y = Number(yTA.value);

        event.preventDefault();

        if(isNaN(x)){
            alert("Please make sure X is a number");
            xTA.value = "<Enter Number>";
            xTA.select();
            return;
        }

        if(isNaN(y)){
            alert("Please make sure Y is a number");
            yTA.value = "<Enter Number>";
            yTA.select();
            return;
        }

        console.log("the value is: " + (x+y));
        resultTA.value = x + y; 

        // WS for same purpose
        ws.send(x + " " + y);
        console.log(ws);
    }
}

// add event listener function to xTA & yTA
xTA.addEventListener("KeyPress", handleKeyPressCB);
yTA.addEventListener("KeyPress", handleKeyPressCB);

// websocket
let ws = new WebSocket("ws://localhost:8080");
ws.onmessage = handleMessageWS;
//ws.onconnect = handleConnectWS; <- onconnect doesn't work
ws.onopen = handleConnectWS; 
ws.onclose = handleCloseWS;
ws.onerror = handleErrorWS;

function handleConnectWS(){
    console.log("WS connected");
}

function handleMessageWS(event){
    console.log("WS sent a message");
    wsTA.value = event.data; 
}

function handleCloseWS(){
    console.log("WS closed");
}

function handleErrorWS(){
    console.log("WS Errored");
}

function handleLoadCB(){
    // this -> XMLHttpResponse object...has a number of fields,
    // one of which is  response text
    console.log("got load");
    resultTA.value = this.responseText;
}

function handleErrorCB(){
    console.log("An AJAX Error has occurred...");
}

let button = document.querySelector('button');
button.addEventListener("click", handleKeyPressCB);