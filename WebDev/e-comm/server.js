var express = require('express');  //we require a module of express

var app = express();  // we create an express app

var port = 8080;  //8080 is a small unsecured port 

app.use(express.static("frontend")); //if the req type is static , the server will search for the res in the 'public' directory


app.listen(port,function(){ //here the function is a callback method 
    console.log("Server is running on http://localhost:"+ port);
})
//What is localhost? it the the current machine that is running the server 


