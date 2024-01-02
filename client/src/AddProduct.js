import React,{useState} from "react";
import axios from "axios";

function Addproduct({ onAddProduct }) {
  const [newProduct, setNewProduct] = useState({product_name: "",price: 0, });
  

  const handleInputChange = (e) => {
    const { product_name, value } = e.target;
    setNewProduct((prevProduct) => ({...prevProduct,[product_name]: value,}));
  };
  const handleAddProduct = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8082/api/add",
        newProduct,
        { withCredentials: true }
      );
      onAddProduct(response.data);
      setNewProduct({ product_name: "", price: 0 });
    } catch (error) {
      console.error("데이터 부적합합니다.", error);
    }
  };
  return (
    <div>
      <h2>상품 추가</h2>
      <div>
        <label>상품명 : </label>
        <input
          type="text"
          name="product_name"
          value={newProduct.product_name}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label> 상품 가격 : </label>
        <input
          type="number"
          name="price"
          value={newProduct.price}
          onChange={handleInputChange}
        />
      </div>
      <button onClick={handleAddProduct}>상품 추가하기</button>
    </div>
  );
}


export default Addproduct;