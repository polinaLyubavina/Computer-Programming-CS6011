"use strict"


let myBody = document.body;
let myHead = document.createElement('h2');
let myText = document.createTextNode("10x10 Multiplication Table");
myHead.style = "text-align: center;";
myHead.appendChild(myText);
myBody.appendChild(myHead);

let myTable = document.createElement('table');
myTable.style = "width: 100%; border: 1px solid black; text-align: center;";

//modifed nested for loop that gives table out to browser instead of #'s out to console
for (let i = 0; i < 11; i++) {

    let myTableRow = document.createElement('tr');

    for (let j = 0; j < 11; j++) {
        let myTableData = document.createElement('td');

        //makes the header/first row
        if(i == 0 && j > 0){
            myTableData.innerText = j;
        } 
        //makes header/first column
        else if(j == 0 && i>0){
            myTableData.innerText = i;
        } 
        else  if(i>=1 && j>=1) {
            myTableData.innerText = i*j;
        }

        myTableData.addEventListener('click', function(event) {
            try {
                document.getElementsByClassName("clicked").item(0).classList.remove("clicked");
            } catch {
                //
            } finally {
                myTableData.classList.add("clicked");
            }
        });

        // Add table data to the row
        myTableRow.appendChild(myTableData);
    }

    // Add the row to the table
    myTable.appendChild(myTableRow);
}

// Add the table to the body
myBody.appendChild(myTable);

// Toggle color
let myP = document.createElement('p');
myP.style = "text-align: center; border: 1px solid black";

let colorButton = document.createElement('button');
colorButton.style = "text-align: center;";
colorButton.innerText = "Toggle Color";
colorButton.addEventListener('click', function(event) {
    myBody.style = "background-color: blue";
});

myP.appendChild(colorButton);
myBody.appendChild(myP);


let colorBool = true;
window.setInterval(() => {
    console.log('here');
    if (colorBool) {
        myBody.style = "background-color: tan;";
    } else {
        myBody.style = "background-color: linen;";
    }
    colorBool = !colorBool;
}, 1000)

