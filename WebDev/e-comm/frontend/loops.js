var arr1 = [9,8,7,6,5];

//for loop with break and continue
for(let i = 0; i < 5 ; i++){
    if(i == 3){
        break;
    }
    if(i == 2 ){
        continue;
    }
    console.log(arr1[i] + " ");
}

//for of loop - returns value
for(let item of arr1){
    console.log(`value of item is ${item}`);
}

//for in loop - return key
for(let item in arr1){
    console.log(`the value is: ${item}`);
}


//for each is used to perform some task on the items in array

// forEach()

// Purpose: To loop through an array and perform a side effect (e.g., logging, updating DOM, mutating something).

// Return value: Always returns undefined.

// When to use: When you just want to do something with each element but don’t need a new array.

const numbers = [1, 2, 3];

numbers.forEach(num => {
  console.log(num * 2); // Logs: 2, 4, 6
});

const result = numbers.forEach(num => num * 2);
console.log(result); // undefined ❌

//MAP
// map()

// Purpose: To transform each element of an array and return a new array.

// Return value: A new array with the same length as the original, with each element transformed.

// When to use: When you want to create a new array based on the old one.
// const numbers = [1, 2, 3];

// const doubled = numbers.map(num => num * 2);
// console.log(doubled); // [2, 4, 6] ✅
