var express = require('express');
const fileSystem = require('fs'); //alias for fs module
// var data[] = require('./db.json'); not recommended
var bodyParser = require('body-parser');
var app = express();


var port = 5050;
//converting the incoming the request body to json

app.use(bodyParser.json());
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

app.get('/products', (req, res) => {
  fileSystem.readFile("./db.json", "utf-8", (error, data) => {
    //first we should handle the error
    if (error) {
      console.log("Error reading the db file");
      res.status(500).json({"message" : "Internal Server Error"});
    }
    //callback that handles error
    // console.log("Data from database ", data);
    const currentDBData = JSON.parse(data);
    // console.log("Formatted data: ", currentDBData);
    const productsFromDB = currentDBData.products;
    res.json(productsFromDB); //returns products as array of objects
  })
})

app.post('/products', (req, res) => {
  //now we build a simple crud operation
  let newProduct = req.body;
    if (newProduct && newProduct.name && newProduct.price) {
      console.log("New product to be added: ", newProduct);
    } else {
      return res.status(400).json({message : "Invalid product request"})
    }
  fileSystem.readFile("./db.json", "utf-8", (error, data) => {
    //first we should handle the error
    if (error) {
      console.log("Error reading the db file");
      return res.status(500).json({message : "Internal Server Error"});
    }
    //callback that handles error
    // console.log("Data from database ", data);
    const currentDBData = JSON.parse(data);
    // console.log("Formatted data: ", currentDBData);
    const productsFromDB = currentDBData.products;
    const updatedProductsData = [...productsFromDB, newProduct]; //use of spread operator
    // const updatedProductsData = productsFromDB.push(newProduct) //not recommended as it works on the og object
    currentDBData.products = updatedProductsData;
    //write in file
    fileSystem.writeFile('./db.json', JSON.stringify(currentDBData), (error, data) => {
      //callback if any error is there in case of failure
      if (error) {
        console.log("Error reading the db file");
        return res.status(500).json({message : "Internal Server Error"});
      }
      return res.status(201).json({message : "Written successfully!"});
    });
  });
});

// Students to implement below 2 methids
// Sample Input: localhost:5000/products/3
// {"name": "Wireless Mouse New"}
app.put("/products/:id", (req, res) => {
  // Update product by ID
  var productIdToUpdate = req.params.id;
  const dataToUpdate = req.body;
  //we will need to first readfile and find the object
  fileSystem.readFile("./db.json", (error, data) => {
    if (error) {
      return res.status(500).json({message: "Failed to read database"});
    }
    const dataFromDB = JSON.parse(data);
    var arrayOfPdts = dataFromDB.products;
    //now we need to find this object 
    arrayOfPdts = arrayOfPdts.map(pdt => 
      pdt.id === productIdToUpdate ? {...pdt, ...dataToUpdate} : pdt
    );
    dataFromDB.products = arrayOfPdts;
    fileSystem.writeFile('./db.json', JSON.stringify(dataFromDB), (error, data) => {
      //callback if any error is there in case of failure
      if (error) {
        console.log("Error reading the db file");
        return res.status(500).json({message : "Internal Server Error"});
      }
      return res.status(201).json({message : "Written successfully!"});
    });
  });
});

// Sample Input: localhost:5000/products/3
app.delete("/products/:id", (req, res) => {
  // Delete product by ID
  var productIdToDelete = req.params.id;
  //we will need to first readfile and find the object
  fileSystem.readFile("./db.json", "utf-8", (error, data) => {
    if (error) {
      return res.status(500).json({message: "Failed to read database"});
    }
    const dataFromDB = JSON.parse(data);
    var arrayOfPdts = dataFromDB.products;
    //now we need to find this object 
    arrayOfPdts = arrayOfPdts.map(pdt => 
      pdt.id === productIdToDelete ? null : pdt //should have used splice
    );
    dataFromDB.products = arrayOfPdts;
    fileSystem.writeFile('./db.json', JSON.stringify(dataFromDB), (error, data) => {
      //callback if any error is there in case of failure
      if (error) {
        console.log("Error reading the db file");
        return res.status(500).json({message : "Internal Server Error"});
      }
      return res.status(201).json({message : `Deleted successfully! ${productIdToDelete}`});
    });
  });
});
///sample input : localhost:5050/products?id=3&name=newNAME
/**put request for /products */
app.put("/products", (req, res) => {  //this type is used when we want dynamic query params
  //
  let params = req.query;
  let body = req.body;
  console.log("PUT request query params: " , params);
  console.log("PUT request body: ", body);

  res.status(501).json({message : "Not implemented!"})

})
app.listen(port, () => {
  console.log(`Server is running on port http://localhost:${port}/`);
});