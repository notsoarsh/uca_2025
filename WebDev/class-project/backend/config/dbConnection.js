//import mongoose
import mongoose from "mongoose"; //creates instance for mongoose library, same used everywhere
//import product routes
try {
  await mongoose.connect('mongodb://127.0.0.1:27017/classdb1', {
    // newUrlParser : true,
    // useUnifiedTopology: true,
  }); //returns promise
  console.log("Connected to MONGODB");
} catch (e) {
  console.log("Unable to connect" , e);
}
//create connection
// var dbConnection = mongoose.connection;
//Sample mongoose commands
// mongoose works as a middleware , as no restrictions on mongodb so we need a safety feature that ensures the data is not unsafe

// mongoose.Schema()  //not necessary but we can give
//mongoose.Model() is the reference to the collection 

// console.log("the db connection instance is: " , dbConnection);
// export default dbConnection;

