"use strict"

// creates canvas 
let myCanvas = document.getElementsByTagName('canvas')[0];
myCanvas.style = "border: 3px solid darkblue; padding 20px;";
myCanvas.height = window.innerHeight;
myCanvas.width = window.innerWidth;

let ctx = myCanvas.getContext('2d'); // gets object with 2D drawing methods
let bees = [];
let done = false;
let startTime = new Date();

// load honey jar image
let imageHoney = new Image();
imageHoney.src = "honeyjar.jpg";

function handleMouseMove(event){
    imageHoney.xPos = event.x;
    imageHoney.yPos = event.y;
}
document.onmousemove = handleMouseMove;

// load bee image
for(let b = 0; b < 6; b++){
    let imageBee = new Image();
    imageBee.src = "bee.jpg";
    bees[b] = imageBee;
    bees[b].xPos = Math.random() * 600;
    bees[b].yPos = Math.random() * 600;
} 

//bee speed
function updateBees() { 
    for(let b = 0; b < 6; b++) {
        bees[b].xPos = bees[b].xPos - (bees[b].xPos - (imageHoney.xPos || 0))/100;
        bees[b].yPos = bees[b].yPos - (bees[b].yPos - (imageHoney.yPos || 0))/100;
    }
}

function beesOnHoney() {
    for(let b = 0; b < 6; b++) {
        if(Math.abs(bees[b].xPos - imageHoney.xPos) < 10 && Math.abs(bees[b].yPos - imageHoney.yPos) < 10) {
            return true;
        }
    }
    return false;
}

function updateScreen(){
    if (beesOnHoney()) {
        alert("GAME OVER"); 
    }

    // Clear the screen
    ctx.clearRect(0, 0, myCanvas.width, myCanvas.height); // erases whatever is already there
    
    // Draw honey jar
    ctx.drawImage( imageHoney, imageHoney.xPos, imageHoney.yPos, 50, 50 );

    // Draw bees
    updateBees();
    for(let bee = 0; bee < bees.length; bee++){
        ctx.drawImage( bees[bee], bees[bee].xPos, bees[bee].yPos, 50, 50 );
    }
}

setInterval(updateScreen, 5);