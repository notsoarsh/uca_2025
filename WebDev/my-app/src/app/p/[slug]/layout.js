import { preloadFont } from "next/dist/server/app-render/entry-base";

function PDPLayout({children}) {
  
  return <>
    <PDPHeader/>
    {children}
  </>
}

function PDPHeader({children}) {
  return <>PDP header</>
}

export default PDPLayout;