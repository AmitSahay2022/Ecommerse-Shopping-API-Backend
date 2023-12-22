package com.sahay.ecombackendapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sahay.ecombackendapi.entity.Category;
import com.sahay.ecombackendapi.entity.Product;
import com.sahay.ecombackendapi.exception.ProductNotFoundException;
import com.sahay.ecombackendapi.repository.ProductRepository;
import com.sahay.ecombackendapi.service.CategoryService;
import com.sahay.ecombackendapi.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryService categoryService;
	@Override
	public String saveProduct(Integer categoryId, Product product) {
		
		Category category = categoryService.getCategoryById(categoryId);
		product.setCategory(category);
		productRepository.save(product);
		return "Product Saved Successfully";
	}

	@Override
	public String updateProduct(Integer productId, Product product) {
		// TODO Auto-generated method stub
		Product productById = getProductById(productId);
		productById.setName(product.getName());
		productById.setPrice(product.getPrice());
		productById.setImageURL(product.getImageURL());
		productById.setDescription(product.getDescription());
		productRepository.save(productById);
		return "Updated Successfully";
	}

	@Override
	public String deleteProduct(Integer productId) {
		Product product = getProductById(productId);
		productRepository.delete(product);
		return "Deleted Successfully";
	}

	@Override
	public Product getProductById(Integer id) {
		Product product = productRepository
				              .findById(id)
				              .orElseThrow(
				            		  ()->new ProductNotFoundException("Product Not Found"));
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryName(category);
	}

}
