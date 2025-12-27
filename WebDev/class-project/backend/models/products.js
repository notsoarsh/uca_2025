import mongoose from "mongoose";

const productsSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  category: {
    type: String,
    required: true,
  },
  stock: {
    type: Number,
    required : true,
  },
  image : {
    type: String,
    required : true,
  } 
});

const ProductModel = mongoose.model("Products", productsSchema);

ProductModel.getAllProducts = async function(successCallback, errorCallback) {
  try {
    const data = await ProductModel.find({});
    successCallback(data);
  } catch (error) {
    errorCallback(error);
  }
}

ProductModel.addNewProduct = async function (newProduct, successCallback, errorCallback) {
  try {
    const createdProduct = await ProductModel.create(newProduct);
    successCallback(createdProduct);
  } catch (error) {
    errorCallback(error);
  }
  
}

export default ProductModel;

// Your JS Code
//    ↓
// Mongoose (Schema + Casting + Validation)
//    ↓
// MongoDB Driver (Node.js)
//    ↓
// MongoDB Server (BSON Storage)