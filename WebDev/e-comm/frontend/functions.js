// tradational way
function fool(param1){
    console.log("Hello");
}

//functional expression
var foo2 = function(param1){
    console.log("Hello");
}

//functional expression with arrow function
var foo3 = (param1) => {
    console.log("Inside foo3");
}

//anonymous function
(param1) => {
    console.log("Anonymous function");
}

//self invoking
((param1) => {
    console.log("Inside self invoking");
})();

//generator function - allows to return multiple values
function* scoreGen(initialScore) {
    let currentScore = initialScore; //returns an generator object iterator
    while(currentScore < 5) {
        currentScore += 1;
        yield currentScore;   // we dont use return statement
    }
}

const currentScore = scoreGen(0);
console.log(currentScore.next());

//We pass arguements when method call, and we declare parameters when we define/declare a function



