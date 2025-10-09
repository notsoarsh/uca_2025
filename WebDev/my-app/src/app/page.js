import Image from "next/image";
import {DescriptionComponent} from "./components/Description"
export default function Home() {
  return (
    <div><DescriptionComponent name = "Valid name"/></div>
  );
}



/**
 * Rendering Techniques
 */
//CSR - Client side rendering
//SSR - Server Side Rendering
//SSG - Static site generation
//ISR - Incremental Static Generation
// Hybrid - some parts of all
// Some - can part of single page or application
