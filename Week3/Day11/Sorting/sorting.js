"use strict"

let myArray = [3, 6, 5, 7, 9, 8];
let myArray2 = [3.7, 6.8, 5.9, 5.7, 3.9, 8.9];
let myArray3 = ["Apple Event", "October 18", "Music Voice", "The Circle", "Apple Music", "Steve Jobs Double"];
let myArray4 = [3, "better brain", "happy people", "google wins", 7.8, 9.7, 8];

let findMinLocation = function(param, compareFunction){
    let minLocation = 0;
    let minValue = param[0];

    for(let j = 1; j < param.length; j++) {
        if(compareFunction(param[j], minValue)){
            minLocation = j;
            minValue = param[j];
        }
    }
    return minLocation;
}

let selectionSort = function(param, compareFunction) {
    for(let o = 0; o < param.length; o++) {
        let minLocation = findMinLocation(param.slice(o, param.length), compareFunction); 
        let a = param[o];
        param[o] = param[minLocation + o]; 
        param[minLocation + o] = a; 
    }
};

let compareFunction = function(a, b) { return a > b };

selectionSort(myArray, compareFunction);
selectionSort(myArray2, compareFunction);
selectionSort(myArray3, compareFunction);
selectionSort(myArray4, compareFunction);
console.log(myArray);
console.log(myArray2);
console.log(myArray3);
console.log(myArray4);

let arrayOfPeople = [{first:"Barack", last:"Obama"}, {first:"Michelle", last:"Obama"}, {first:"Queen", last:"Elizabeth"}, {first:"Count", last:"Dracula"}];

let nameCompareByLast = function(a, b) {
    if(a.last === b.last){
        return a.first < b.first;
    }
    return a.last < b.last
};

selectionSort(arrayOfPeople, nameCompareByLast);
console.log(arrayOfPeople.map((person) => person));

let nameCompareByFirst = function(a, b) {
    if(a.first === b.first){
        return a.last < b.last;
    }
    return a.first < b.first
};

selectionSort(arrayOfPeople, nameCompareByFirst);
console.log(arrayOfPeople);