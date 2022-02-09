"use strict"

/////////////////////////////////////////////

// let bgInterval = setInterval(30, //clear canvas); 


/////////////////////////////////////////////
/////////////////////////////////////////////

// creates canvas 
let myCanvas = document.getElementsByTagName('canvas')[0];
myCanvas.style = "border: 3px solid darkblue; padding 20px;";
myCanvas.height = window.innerHeight;
myCanvas.width = window.innerWidth;

let ctx = myCanvas.getContext('2d'); // gets object with 2D drawing methods
let bees = [];
let done = false;
let startTime = new Date();

/////////////////////////////////////////////

//request the next frame
if(!done){
    window.requestAnimationFrame(updateScreen);
} //ends updateScreen()

function handleMouseMove(event){
    imageHoney.xPos = event.x;
    imageHoney.yPos = event.y;
}

/////////////////////////////////////////////

// updates bees' speed
let endTime = new Date();
// converts to seconds
let timeDiff = (endTime - startTime) / 100;

if(timeDiff > 5){
    startTime = endTime;
    console.log("bees speed up");
    for(let i = 0; i < bees.length; i++){
        let bee = bees[i];
        bee.speed += 2;
    }
}

let bee = {img:{}, speed: Math.random()/2.0 + 0.5};

/////////////////////////////////////////////

// load honey jar image
let imageHoney = new Image();
imageHoney.src = "honeyjar.jpg";

// load bee image
let imageBee = new Image();
imageBee.src = "bee.jpg";

/////////////////////////////////////////////

let theParag = document.querySelector( 'p' );
theParag.addEventListener( "click", handleClick );

// let myDiv = document.getElementsByTagName( 'div' )[0];
// console.log( "myDiv: " + myDiv );
// myDiv.style = "border: 1px solid red; padding: 20px;";

function handleError() {
    console.log( "There was an error" );
}

function handleLoad() {
    myDiv.innerHTML = this.responseText;
}

let delta = 10;

imageHoney.xPos = 100;

// function draw() {

//     if( imageHoney.xPos > myCanvas.width ) {
//         delta = -10;
//     }
//     else if( imageHoney.xPos < 0 ) {
//         delta = 10;
//     }

//     imageHoney.xPos = imageHoney.xPos + delta ;

//     ctx.fillRect( 10, 10, 1000, 500 );
//     ctx.drawImage( imageHoney, imageHoney.xPos, 0 );

//     window.requestAnimationFrame( draw );
// }

// window.requestAnimationFrame( draw );

/////////////////////////////////////////////

function handleClick() {
    let xhr = new XMLHttpRequest();
    xhr.open( "GET", "data.html" );
    xhr.addEventListener( "load", handleLoad );
    xhr.addEventListener( "error", handleError );
    xhr.send();
}

/////////////////////////////////////////////

function beOnHoney(bee){
    let tarX = imageHoney.xPos;
    let tarY = imageHoney.yPos;
    let distance = Math.sqrt(Math.pow(bee.pos.x - tarX, 2) + Math.pow(bee.pos.y - tarY, 2));
    if(distance < 20){
        done = true;
    }
}

/////////////////////////////////////////////

function updateScreen(){
    ctx.clearRect(0, 0, myCanvas.width, myCanvas.height); // erases whatever is already there
}

/////////////////////////////////////////////

//// update the bees
let tarX = imageHoney.xPos;
let tarY = imageHoney.yPos;

for(let i = 0; i < bees.length; i++){
    let bee = bees[i];
    let coin = Math.random();
    if(coin > .95){
        if(bee.imageBee == 1){   // curImg
            bee.imageBee = 2;    // curImg
        }
        else {

        }
    }
}

/////////////////////////////////////////////

function drawOnMove( event ) {
    updateScreen();
    console.log("move is at: " + event.x + ", " + event.y );
    ctx.drawImage( imageHoney, event.x, event.y, 50, 50 );
    event.updateScreen; 
}

/////////////////////////////////////////////

document.onmousemove = drawOnMove;

/////////////////////////////////////////////