// AddProduct.js
import React, { useState } from "react";
import axios from "axios";

function AddProduct({ onAddProduct }) {
  const [newProduct, setNewProduct] = useState({ product_name: "", price: 0 });

  const handleInputChange = (e) => {
    const { product_name, value } = e.target;
    setNewProduct((prevProduct) => ({ ...prevProduct, [product_name]: value }));
  };

  const handleAddProduct = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8081/api/add",
        newProduct,
        { withCredentials: true }
      );
      onAddProduct((prevProducts) => [...prevProducts, response.data]);
      setNewProduct({ product_name: "", price: 0 });
    } catch (error) {
      console.error("error:", error);
    }
  };

  return (
    <div>
      <h2>새로운 제품 추가</h2>
      <div>
        <label>이름:</label>
        <input
          type="text"
          name="name"
          value={newProduct.product_name}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label>가격:</label>
        <input
          type="number"
          name="price"
          value={newProduct.price}
          onChange={handleInputChange}
        />
      </div>
      <button onClick={handleAddProduct}>제품 추가</button>
    </div>
  );
}

export default AddProduct;
