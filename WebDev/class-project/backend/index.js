import express from "express";
import fileSystem from "fs"; //alias for fs module
// var data[] = import('./db.json'); not recommended

import "./config/dbConnection.js";
import productRoutes from "./routes/products.js"; //default as not in {}
import bodyParser from "body-parser";
var app = express();


var port = 5050;
//converting the incoming the request body to json

app.use(bodyParser.json());
// '/*splat' means accepts all routes expect main
app.use("/*splat", function (req, res, next) { //sets the middleware, *splat -> all paths except root path,if we want root as well put *splat in bracket
  //allow cors
  res.header("Access-Control-Allow-Origin", "http://localhost:3000");
  res.header(
    "Access-Control-Allow-Methods",
    "GET, POST, PUT, PATCH, DELETE"
  );
  res.header(
    "Access-Control-Allow-Headers",
    "Content-Type"
  )
  next();
});

app.get('/', (req, res) => {
  res.end("Hello world");
})

//middleware to forward request for /products
app.use("/products", productRoutes);



// Students to implement below 2 methids
// Sample Input: localhost:5000/products/3
// {"name": "Wireless Mouse New"}

// Sample Input: localhost:5000/products/3

///sample input : localhost:5050/products?id=3&name=newNAME
/**put request for /products */

app.listen(port, () => {
  console.log(`Server is running on port http://localhost:${port}/`);
});
