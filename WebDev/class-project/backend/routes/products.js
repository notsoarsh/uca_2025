import express from "express";
import bodyParser from "body-parser";
import fileSystem from "fs";
const router = express.Router();
import ProductModel from "../models/products.js";

router.get("/", async (req, res) => {
    await ProductModel.getAllProducts(
      (data) => {
        res.json(data);
      },
      (error) => {
        res.status(500).json({error: error.message}); //catch has mostly 500 as these are exceptions server side
      }
    );
    
    // return error.message;
  
});


router.post("/", async (req, res) => {
    const newProduct = req.body;
    console.log("New product to be added" , newProduct);
    await ProductModel.addNewProduct(
      newProduct,
      (data) => {
        res.json(data);
      },
      (error) => {
        res.status(500).json({error: error.message}); //catch has mostly 500 as these are exceptions server side
      }
    );
    
    // return error.message;
  
});

export default router;

//these callbacks are helpful when the request is multistep like auth
//auth -> validation -> processing
//route <- authenticate <- validate <- processing