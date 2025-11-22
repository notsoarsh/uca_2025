"use client";
import Button from "../components/button";
import { useRef } from "react";

function AddProductPage() {

  const nameRef = useRef(null);
  const priceRef = useRef(null);
  const imageRef = useRef(null);

  const submitHandler = () => {
    alert("Submit button clicked");

    const name = nameRef.current.value;
    const price = priceRef.current.value;
    const image = imageRef.current.value;

    const productData = { name, price, image };

    console.log("Product data:", productData);

    // fetch must be inside submitHandler
    fetch("http://localhost:5050/products", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"  // required
      },
      body: JSON.stringify(productData),
    })
      .then((response) => {
        console.log("Response from server:", response);
        alert("Product Added Successfully")
      })
      .catch((error) => {
        console.log("Error sending product:", error);
      });
  };

  return (
    <div style={{ margin: "50px" }}>
      <form className="max-w-sm mx-auto" onSubmit={(e) => e.preventDefault()}>
        
        <div className="mb-5">
          <label htmlFor="name" className="block mb-2.5 text-sm font-medium text-heading">
            Name
          </label>
          <input
            type="text"
            id="name"
            ref={nameRef}
            className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow placeholder:text-body"
            placeholder="Product Name"
            required
          />
        </div>

        <div className="mb-5">
          <label htmlFor="price" className="block mb-2.5 text-sm font-medium text-heading">
            Price
          </label>
          <input
            type="number"
            id="price"
            ref={priceRef}
            className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow placeholder:text-body"
            placeholder="$123"
            required
          />
        </div>

        <div className="mb-5">
          <label htmlFor="image" className="block mb-2.5 text-sm font-medium text-heading">
            Image URL
          </label>
          <input
            type="url"
            id="image"
            ref={imageRef}   //  ref on input, not label
            className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow placeholder:text-body"
            placeholder="https://abc.com/placeholder"
            required
          />
        </div>

        <Button variant="light" onClick={submitHandler}>
          Submit
        </Button>

      </form>
    </div>
  );
}

export default AddProductPage;
