"use strict"

document.writeln("Hello!!");

console.log("Hello again!!");   // with the console the user will not see it on the webpage

//creates array
let myArray = ["Hello my World", true, 7, 8.7, {x : 5}];
console.log(myArray);
// modified array
myArray[2] = 10;
console.log(myArray);

//C++ style function syntax
function myFunction(x, y) {
    return x + y;
}
console.log(myFunction(1, 2));
console.log(myFunction("string", "hello"));

let myFunction2 = function(a, b) {return a - b};
console.log(myFunction2(7, 2));
console.log(myFunction2("string", "hello"));
console.log(myFunction2(7, 2.78));

