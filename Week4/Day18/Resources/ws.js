"use strict"

let myWS = new WebSocket("ws://" + location.host);
myWS.onmessage = handleMessage;
myWS.onopen = handleConnect;
myWS.onclose = handleClose;
myWS.onerror = handleError;

let join = document.querySelector('#join');
join.onclick = joinRoom;

let send = document.querySelector('#send');
send.onclick = sendMessage;

let textbox = document.querySelector('#messageArea');

let username = document.querySelector('#username');

let receiverArea = document.querySelector('#receiverArea');


////////
// web socket functions

function handleConnect(){
    console.log("WS connected");
}

function handleMessage(event){
    console.log("WS sent a message", event);
    const mess = event.data.split(' ');
    const newMessage = document.createElement('p');
    newMessage.innerText = `${mess[0]}: ${mess.slice(1).join(' ')}`;
    document.querySelector('#receiverArea').appendChild(newMessage);
}

function handleClose(){
    console.log("WS closed");
}

function handleError(){
    console.log("WS Errored");
}

function joinRoom() {
    console.log('joining');
    myWS.send('join mainRoom');
}

///////
// sendMessage function

function sendMessage(event){
    myWS.send(`${username.value} ${textbox.value}`);
    console.log(`${username.value} ${textbox.value}`);
}
