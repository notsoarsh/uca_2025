function promiseExecutor() {
  //async
  setTimeout(() => {
    console.log("Promise 1 executed");
  }, 5000);
}

function customPromise(executor) {
  this.state = "pending"
  executor();
}

var customPromise1 = new customPromise(promiseExecutor);


//-----------------------------------------

function customPromiseExecutor(resolve, reject) {
  //async
  setTimeout(() => {
    console.log("Promise 1 executed");
    resolve("Promise 1 resolved");
    //reject("Promis 1 rejected");
  }, 5000);
}

function PromiseCustom(executorFunArg) {
  this.state = "pending";

  executorFunArg((responseArg) => {
    this.state = "fulfilled";
  }, (errorArg) => {
    this.state = "rejected"
  });
}

var customPromise1 = new PromiseCustom(customPromiseExecutor);

//resolve state change krta hai - fullfill
//reject state change krta hai - rejected

// Simple custom promise with then and catch methods
function PromiseCustom(executorFunArg) {
  this.state = "pending";
  let successCallBack;
  let failureCallBack;
  this.then = function(callback) {
    successCallBack = callback();
  };//this excepts a function as an arguement
  this.catch = function() {
    failureCallBack = callback();
  };

  executorFunArg(
    (responseArg) => {
    this.state = "fulfilled";
    successCallBack(responseArg);
  }, (errorArg) => {
    this.state = "rejected";
    failureCallBack(errorArg);
  });
}


// <------------------------------------------------------------------>

function customPromiseExecutor(resolve, reject) {
  //async
  setTimeout(() => {
    console.log("Promise 1 executed");
    resolve("Promise 1 resolved");
    //reject("Promis 1 rejected");
  }, 5000);
}
var customPromise1 = new PromiseCustom(customPromiseExecutor);
function PromiseCustom(executorFunArg) {
  this.state = "pending";
  let successCallBack;
  let failureCallBack;
  this.then = function(callback) {
    successCallBack = callback();
  };//this excepts a function as an arguement
  this.catch = function() {
    failureCallBack = callback();
  };

  executorFunArg(
    (response) => {
    this.state = "fulfilled";
    successCallBack(response);
  }, (error) => {
    this.state = "rejected";
    failureCallBack(error);
  });
}
