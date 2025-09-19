// (function foo(){
//     console.log("Hello World")
//     document.getElementById("myElement").innerHTML = "Content Updated!";

// })();
// // this a syntax of a self invoking function

// function foo(){
//     console.log("Hello World");
//     if(document.getElementById("myElement")){
//         document.getElementById("myElement").innerHTML = "Delayed message!";
//     }
//    //here we use a ? so to handle the run time error
//     //this will not be executed as it can potentially be null
//     //so we use defer 
    

// }

// setTimeout(() => {
//     console.log("This is a delayed version");
// },5000);  
//This is a setTimeout function that accepts the function as the first parameter and delay in ms as the second para

var productsList = [];



function loadProducts(renderProductsWithData){
    
    setTimeout(() => {
        productsList = productsListFromServer;

        renderProductsWithData();
    }, 2000);
        
}

//arrow function in js  ()=>{}
const foo = (arg1) => {
    console.log(arg1);
};  


function renderProducts(){
    // document.getElementById("tag2").innerHTML = arr1;
    function renderProductsWithData(){
        if(productsList.length === 0){
            document.getElementById("data").innerHTML = " Loading data from the server ....";
            return;
        }
        document.getElementById("data").innerHTML= `
    <table>
            <thead>
                <tr>
                    <th>Serial Number</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    
                </tr>
            </thead>
            <tbody>
                ${productsList.map((product,index) => {
                    return `
                    <tr>
                        <td>
                            ${index+1}
                        </td>
                        <td>
                            ${product.name}
                        </td>
                        <td>
                            ${product.price}
                        </td>
                        <td>
                            ${product.desc}
                        </td>
                    </tr>
                    `
                }).join('')}  
            </tbody>
        </table>
        `
    }
    renderProductsWithData(); // Show "Loading..." message initially   //this is the data hold event
    loadProducts(renderProductsWithData); // Load products and then render them
    
}



renderProducts();
var app = document.getElementById("reactapp");
var root = ReactDOM.createRoot(app);
root.render(<h1>Hello From React</h1>); //this code is not html it is actually jsx code which browser cant understand , so we use a transpiler like babel to transpile this so browser can undertand
//xhr - api typa shi


