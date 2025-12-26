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

ProductModel.getAllProducts = async function() {
  const data = await ProductModel.find({});
  return data;
}

ProductModel.addNewProduct = async function (newProduct) {
  const createdProduct = await ProductModel.insertOne(newProduct);
  return createdProduct;
}

export default ProductModel;

// Your JS Code
//    ↓
// Mongoose (Schema + Casting + Validation)
//    ↓
// MongoDB Driver (Node.js)
//    ↓
// MongoDB Server (BSON Storage)