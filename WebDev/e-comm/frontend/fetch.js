const { response } = require("express");

fetch("www.google.com") //fetch is used to make api calls , returns a promise

var x = fetch("http://localhost:8080/data");
// x is a promise

x.then((response) => { console.log("Response is + " + response) });


//fetch is just a wrapper around xmlHTTPRequest method which returns a promise
//The primary uses of promises is to handle async ops
// One of the most common async ops in web is making network req
//the network req are basicllly made to fetch some data from servers
//We will see how xmlHTTPRequest constructor func is used ot make network req and "fetch" custo implementation using this xmlHTTPRequest

var xmlHTTPRequest1 = new XMLHttpRequest();
xmlHTTPRequest1.open("GET", "http:localhost:8080/data");
xmlHTTPRequest1.send();

xmlHTTPRequest1.onreadystatechange = function () { //har bar call hoga when state is changed
  if (xmlHTTPRequest1.readyState === 4) { //4 is ready state
    console.log("xmlHTTPRequest1 contents :", xmlHTTPRequest1);
    console.log("Response recieved from server is: ", xmlHTTPRequest1.responseText);
  }
}

//implement customFetch using xmlHTTPRequest
//customFetch accepts a URL 
//returns a promise
//the promise should be resolved asap the req is complete

function customFetch(url) {
  // return Promise
  return new promise1(executorFunc);

  function executorFunc(resolve, reject) {
    var xmlHTTPRequest1 = new XMLHttpRequest();
    xmlHTTPRequest1.open("GET", url);
    xmlHTTPRequest1.send();

    xmlHTTPRequest1.onreadystatechange = function () { //har bar call hoga when state is changed
      if (xmlHTTPRequest1.readyState === 4) { //4 is ready state
        console.log("Response recieved from server is: ", xmlHTTPRequest1.response);
      };
      resolve(xmlHTTPRequest1.response);
    };
  }
}

var data = customFetch("http://localhost:8080/data");
data.then((response) => {
  console.log("Response from custom fetch is: ", response);
});

