import Button from "./button";
function ProductsCard({product}) {
  return (
    <>
  <div style={{border:"2px solid black", margin : "10px", padding:"5px"}}>
        <img src={product.image} alt="Product Image"/>
        <div style={{display: "flex", justifyContent: "center"}}>
          <div>{product.name}&nbsp;&nbsp;</div>
          <div>{product.price}&nbsp;&nbsp;</div>
          <div>{product.tag}</div>
          <div style={{textAlign: "center"}}>
            <Button variant="primary">Add to Card</Button>
          </div>
          </div>
      </div>
      </>
  )
}

export default ProductsCard;