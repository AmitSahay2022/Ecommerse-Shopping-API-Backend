package com.sahay.ecombackendapi.service;

import java.util.List;

import com.sahay.ecombackendapi.entity.Product;

public interface ProductService {
	String saveProduct(Integer categoryId, Product product);

	String updateProduct(Integer productId, Product product);

	String deleteProduct(Integer productId);

	Product getProductById(Integer id);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);
}
