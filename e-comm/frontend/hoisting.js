//Function vs Block scope
 //Function scope created by a function
 //block scope are created by {} (eg if,else,for,etc)

 //Hoisting - move the declaration to the top of the scope
 //Hoisting applies to var, function , let , const, class, import

//Hoisting of var and let

// var hoisting is moving the var declaration to the top of the innermost function
// let hoisting is moving the let declaration to the top of the innermost block scope 

function foo(){
    //var will be hoisted here 
    console.log("var x before is : ",x); //undefined 
    console.log("let y before is: ",y) //reference error , y not defined yet
    if(true){
        //let will be hoisted to the top of the function
        var x = 2;
        let y = 3;
    }
}

function foo(){
    if(true){
        var x = 2;
        let y = 3;
    }
    console.log("var x after is " ,x); //2
    console.log("let y after is ",y); //reference y is not defined
}
 
function foo(){
    // var x hoisted here
    if(true){
        // let y hoisted here 
        console.log("var x after is: ",x)//undefined
        console.log("let y after is: ",y)//referencederror : cannot access y before initialisation
        var x = 2;
        let y = 3;
    }
    console.log("var x after is ",x);
    console.log("let y after is ",y);
}

function foo(){
    // var x hoisted here
    if(true){
        // let y hoisted here 
        var x = 2;
        let y = 3;
        console.log("var x after is: ",x)//2
        console.log("let y after is: ",y)//3

    }
    console.log("var x after is ",x);//2
    console.log("let y after is ",y);// reference error : y not defined
}

//Function hoisting
fooF();
function fooF(){
    console.log("Function called");
}

fooVar();
var fooVar =  function(){
    console.log("Function called");
}

//here let not defined as no block scope
fooLet();
let fooLet = function(){
    console.log("Function called")
}

function foo(){
    fooL(); //here fooL defined but not initialised 
    let fooL = function(){
        console.log("Function called fool")
    };
}