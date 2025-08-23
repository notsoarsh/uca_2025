//spread operator
var num1 = [1,2,3,4];
var num2 = [5,6,7,8];

var combinedArray = [...num1,...num2];

var arr1 = new Array(...[1,4242,11,24]);
var arr2 = new Array(1,2,3,4);
var student1ObjDetail = {name: "name1", age: 20};
var student2ObjDetail = {name: "name1", age: 20};

var studentCombined = {...student1ObjDetail,...student2ObjDetail};

//Rest Operator
// sumAll(1,2);
// sumAll(1,2,3,4,5,6,7,8);
function sumAll(...items) {
    let sum = 0;
    for(let i =0; i < items.length; i++){
        sum += items[i];
    }
    return sum;
}

let totalSum = sumAll(3,4,5,6);
console.log(totalSum);
