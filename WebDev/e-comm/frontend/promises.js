//Something in future needs to be done, say for eg HTTP calls.
// The Promise object represents the eventual completion (or failure) of an asynchronous operation and its resulting value.


//Promise is an OBJECT created using Promise Constructor function and primarily have, 2 properties.
//State - Pending, FulFilled, rejected
// Result - Any valid value or undefined
//Executor functionn is called immediately when promise is created
//Executor func is called with 2 args of type function - resolve and reject
// resolve and reject are functions

function promiseExecutor() {
  //async op
  setTimeout(() => {
    console.log("Promise 1 executed");
  }, 5000);
}
var promise1 = new Promise(promiseExecutor);

function promiseExecutor(resolve, reject) {
  //async
  setTimeout(() => {
    console.log("Promise 1 executed");
    resolve("Promise 1 resolved");
    // reject("Promise 1 rejected");
  }, 5000);
}

var promise1 = new Promise(promiseExecutor);

// promise1.then //Accepts function as an argument and is passed by then

//Simple promise with additional then and catch
function promiseExecutor(resolve, reject) {
  //async
  setTimeout(() => {
    console.log("Promise 1 executed");
    resolve("Promise 1 resolved");
    // reject("Promise 1 rejected");
  }, 5000);
}
var promise1 = new Promise(promiseExecutor);
promise1.then((resolvedReturnedValue) => {
  alert("Promise resolved with value: "+ resolvedReturnedValue);
});
promise1.catch((rejectedReturnValue) => {
  alert("Promise rejected value: "+ rejectedReturnValue);
});



/////////////////////////////////////////////////////////////// 
// Custom Promises 

function customPromiseExecFunc(resolve, reject) {
  setTimeout(() => {
    console.log("Promise 1 executed")
    resolve("Promise is fullfilled");
    //reject("Promise rejected");
  }, 5000);
}
function PromiseCustom(executorFunc) {
  this.state = "pending";
  let successCallback;
  let failureCallback;
  
  this.then = function(callback) {
    successCallback = callback; 
  };
  this.catch = function() {
    failureCallback = callback;
  };

  executorFunc(
    (response) => {
      this.state = "fullfilled";
      successCallback(response);
    },
    (error) => {
      this.state = "rejected";
      failureCallback(error);
    }
  );
}

var customPromise1 = new PromiseCustom(customPromiseExecFunc);

customPromise1.then((resolvedReturnVal) => {
  alert("Promise resolved with value " + resolvedReturnVal); 
})

customPromise1.catch((rejectedReturnValue) => {
  alert("Promise rejected with value: " + rejectedReturnValue);
})


