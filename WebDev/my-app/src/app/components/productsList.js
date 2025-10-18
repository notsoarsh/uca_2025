import ProductsCard from "./productCard";
function ProductsList({products}) {
  return <>
    <div style={{display: "flex"}}>
    {
      products.map((product) => (
        <ProductsCard key={product.id} product={product}/>
      ))
    }
    </div>

    
  </>
}

export default ProductsList;