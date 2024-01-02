import React, { useState, useEffect } from "react";
import axios from "axios";

const App = () => {
  const [products, setProducts] = useState([]);
  const [newProduct, setNewProduct] = useState({ name: "", price: 0 });

  useEffect(() => {
    axios
      .get("http://localhost:8081/api/item")
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.error(":", error);
      });
  }, []);

  const handleAddProduct = () => {
    axios
      .post("http://localhost:8082/api/add", newProduct)
      .then((response) => {
        setProducts((prevProducts) => [...prevProducts, response.data]);
        setNewProduct({ name: "", price: 0 });
      })
      .catch((error) => {
        console.error("추가 에러:", error);
      });
  };

  return (
    <div>
      <h2>상품 리스트</h2>
      <ul>
        {products.map((product) => (
          <li key={product.id}>
            {product.name} - ${product.price}
            <button onDoubleClick={() => handleEditProduct(product)}>수정하기</button>
            <button onDoubleClick={() => handleDeleteProduct(product)}>삭제하기</button>
          </li>
        ))}
      </ul>

      <h2>상품 추가</h2>
      <label>상품명:</label>
      <input
        type="text"
        value={newProduct.name}
        onChange={(e) => setNewProduct({ ...newProduct, name: e.target.value })}
      />

      <label>가격:</label>
      <input
        type="number"
        value={newProduct.price}
        onChange={(e) =>
          setNewProduct({ ...newProduct, price: e.target.valueAsNumber })
        }
      />

      <button onClick={handleAddProduct}>상품추가</button>
    </div>
  );
};

export default App;
