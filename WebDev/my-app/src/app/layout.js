export const metadata = {
  title : "E-Comm App",
  description : "An e-comm app built in next.js"
}
function Layout({children}) {
  return (
    <html lang="en">
      <body>
        {children}
      </body>  
    </html>
  );
}

export default Layout;

//renders html and then the children
//layout takes in components and renders inside the body
//children prop hai, jo prop diya hai use runtime pe build krta aur layout js ka children component banake bind krta


