var express = require('express');
const fileSystem = require('fs'); //alias for fs module
// var data[] = require('./db.json'); not recommended
var bodyParser = require('body-parser');
var app = express();

var port = 5050;
//converting the incoming the request body to json

app.use(bodyParser.json());

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
  fileSystem.readFile("./db.json", (error, data) => {
    if (error) {
      return res.status(500).json({message: "Failed to read database"});
    }
    const dataFromDB = JSON.parse(data);
    var arrayOfPdts = dataFromDB.products;
    //now we need to find this object 
    arrayOfPdts = arrayOfPdts.map(pdt => 
      pdt.id === productIdToDelete ? null : pdt
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

app.listen(port, () => {
  console.log(`Server is running on port http://localhost:${port}/`);
});