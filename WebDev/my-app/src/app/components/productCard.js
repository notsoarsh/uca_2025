function ProductsCard({product}) {
  return (
    <>
  <div style={{border:"2px solid black", margin : "10px", padding:"5px"}}>
        <img src={product.image} alt="Product Image"/>
        <div style={{display: "flex", justifyContent: "center"}}>
          <div>{product.name}&nbsp;&nbsp;</div>
          <div>{product.price}&nbsp;&nbsp;</div>
          <div>{product.tag}</div>
          </div>
      </div>
      </>
  )
}

export default ProductsCard;