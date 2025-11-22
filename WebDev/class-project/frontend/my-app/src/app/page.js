// import Image from "next/image";
// import {DescriptionComponent} from "./components/Description"
// export default function Home() {
//   return (
//     <div><DescriptionComponent name = "Valid name"/></div>
//   );
// }

import Link from "next/link";
import ProductsList from "./components/productsList";
import Button from "./components/button";
import sleep from "./common/utils";

/**
 * Rendering Techniques
 */
//CSR - Client side rendering
//SSR - Server Side Rendering
//SSG - Static site generation
//ISR - Incremental Static Generation
// Hybrid - some parts of all
// Some - can part of single page or application
export const revalidate = 0; // Disable caching for this page, prevents prerendering
// export const revalidate = n; // Sets cache for revalidating period 

export default async function Home() {

  // ----- SIMULATING API CALL ---------
  
  //here setTimeout will be rendered server side as node also has the functionality, so this is handled by next js

  await sleep(2000);
  console.log("Slept for 10 secs");

  //fetch - its a method we give url to and it makes a http req to the url and it returns a promise
  const productsFromJServer = await fetch("http://localhost:5050/products", );
  const productDataFromJServer = await productsFromJServer.json();  //we get productsFromJServer as a promise and this also needs to be awaited and json has t
  console.log("----------- Fetched produts from server : ", productDataFromJServer);
  // const products = [
  //   {id : 1, name: "product1", price: "50$", tag: "top-seller", image: "https://placehold.co/300x200"},
  //   {id : 2, name: "product2", price: "10$", tag: "recommended", image: "https://placehold.co/300x200"},
  //   {id : 3, name: "product3", price: "100$", tag: "on-sale", image: "https://placehold.co/300x200"},
  // ];

  return(
    <>
    <div style={{ textAlign: "center" }}>
      <h1>Welcome to the Books E-Commerce</h1>

      <div
        style={{
          display: "flex",
          justifyContent: "center",
          gap: "20px",
          marginTop: "20px",
        }}
      >
      {/* <div>List of products</div> */}
      <ProductsList products={productDataFromJServer}/>
      </div>
        <div>
          <section className="border-2 border-solid py-8">
          <div className="max-w-6xl mx-auto text-center px-4">
            <h2 className="text-4xl font-bold mb-4">
              Discover the Best Deals Online
            </h2>
            <p className="text-lg text-gray-600 mb-6">
              Shop thousands of products from top brands at unbeatable prices.
            </p>
          </div>
          <Button variant="light">Take me to Login page</Button>
        </section>
        </div>
    </div>
    </>
  );

}


//this is for the default route 