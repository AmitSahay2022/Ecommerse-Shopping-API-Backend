package com.sahay.ecombackendapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahay.ecombackendapi.entity.Product;
import com.sahay.ecombackendapi.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
	private ProductService productService;

	@PostMapping("{cattgoryId}")
	public ResponseEntity<String> saveProduct(@RequestBody Product product, @PathVariable Integer cattgoryId) {
		return new ResponseEntity<String>(productService.saveProduct(cattgoryId, product), HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product,@PathVariable Integer id){
		return new ResponseEntity<String>(productService.updateProduct(id, product),HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
		return new ResponseEntity<String>(productService.deleteProduct(id),HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id){
		return new ResponseEntity<Product>(productService.getProductById(id),HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
	}
	@GetMapping("category")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category){
		return new ResponseEntity<>(productService.getProductsByCategory(category),HttpStatus.OK);
	}
}
