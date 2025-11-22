import Link from "next/link";
import Button from "./button";
function Header() {
  return (
    <header>
      <div style={{backgroundColor : "grey", padding: "1px 20px", margin:"0 -20px", display:"flex", justifyContent: "space-between", textAlign: "center"}}>
        <Link href="/">
          <h1 style={{margin: 0}}>E-Com</h1></Link>
          <Link href="/login">
          <Button variant="primary">Login
            </Button>
          </Link>
          <Link href="/addproduct">
            <h2>Add product</h2>
          </Link>
        </div>
    </header>
  );
}
export default Header;