package com.kh.springchap1.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kh.springchap1.model.Product;
import com.kh.springchap1.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/item")
	public ResponseEntity <List<Product>>getAllProduct(){
		//return productRepository.findAll();
		List <Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		Product saveProduct = productRepository.save(product);
		return ResponseEntity.ok(saveProduct);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		productRepository.deleteById(id);
		return ResponseEntity.ok("삭제완료");
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct){
		Product existProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("아이디를 찾을수 없다" + id));
		existProduct.setProduct_name(updateProduct.getProduct_name());
		existProduct.setPrice(updateProduct.getPrice());
		
		Product updateProduct = productRepository.save(existProduct);
		return ResponseEntity.ok(updateProduct);
		
	}
}
