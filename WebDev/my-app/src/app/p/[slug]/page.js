async function ProductDetailsPage ({params}) {
  const slug = await params.slug; //
  
  return (<>
    {slug}
  </>
  )
}

export default ProductDetailsPage;