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

export default function Home() {
  const products = [
    {id : 1, name: "product1", price: "50$", tag: "top-seller", image: "https://placehold.co/300x200"},
    {id : 2, name: "product2", price: "10$", tag: "recommended", image: "https://placehold.co/300x200"},
    {id : 3, name: "product3", price: "100$", tag: "on-sale", image: "https://placehold.co/300x200"},
  ];
  return(
    <>
    <h1>Welcome to next app!</h1>
      <Link href="/login">Go to login</Link>
      <div>List of products</div>
      <ProductsList products={products}/>
    </>
  );

}


//this is for the default route 