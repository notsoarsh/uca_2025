import "@/app/global.css";
import { Suspense } from "react";
import Header from "@/app/components/header"

export const metadata = {
  title : "E-Comm App",
  description : "An e-comm app built in next.js"
}
function Layout({children}) {  
  return (
    <html lang="en">
      <body>
        <Header/>
        <Suspense fallback={
          <h1 style={{textAlign:"center", marginTop: "20px"}}>Loading products...</h1>
        }>
          {children}
        </Suspense>
      </body>  
    </html>
  );
}

export default Layout;

//renders html and then the children
//layout takes in components and renders inside the body
//children prop hai, jo prop diya hai use runtime pe build krta aur layout js ka children component banake bind krta
// all the tags written in react are react tags (jsx) they look alike but they are not


