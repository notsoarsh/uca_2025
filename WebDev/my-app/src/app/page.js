// import Image from "next/image";
// import {DescriptionComponent} from "./components/Description"
// export default function Home() {
//   return (
//     <div><DescriptionComponent name = "Valid name"/></div>
//   );
// }

import Link from "next/link";
import ProductsList from "./components/productsList";



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
  function sleep(sleepTime) {
    return new Promise ((resolve) => setTimeout(resolve, sleepTime));
  }
  //here setTimeout will be rendered server side as node also has the functionality, so this is handled by next js

  await sleep(10000);
  console.log("Slept for 10 secs")
  const products = [
    {id : 1, name: "product1", price: "50$", tag: "top-seller", image: "https://placehold.co/300x200"},
    {id : 2, name: "product2", price: "10$", tag: "recommended", image: "https://placehold.co/300x200"},
    {id : 3, name: "product3", price: "100$", tag: "on-sale", image: "https://placehold.co/300x200"},
  ];

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
      <ProductsList products={products}/>
      </div>
    </div>
    </>
  );

}


//this is for the default route 