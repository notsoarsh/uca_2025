//JSON stands for Javacript Object Notation

//for eg
let student = {
    name : "name1",
    age : 20
}

console.log(student);

//saving the object in the local storage
console.log(JSON.stringify(student));

//converting json string back to obj
JSON.parse(localStorage.getItem(student));

//when data is transferred over the network layer, it is sent as a string