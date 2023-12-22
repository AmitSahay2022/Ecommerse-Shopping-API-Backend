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
import org.springframework.web.bind.annotation.RestController;

import com.sahay.ecombackendapi.entity.Category;
import com.sahay.ecombackendapi.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<String> saveCategory(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<String> updateCategory(@PathVariable Integer id,@RequestBody Category category){
		return new ResponseEntity<String>(categoryService.updateCategory(id, category),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
		return new ResponseEntity<String>(categoryService.deleteCategory(id),HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
		return new ResponseEntity<Category>(categoryService.getCategoryById(id),HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Category>> viewAllCategory(){
		return new ResponseEntity<List<Category>>(categoryService.getAllCategory(),HttpStatus.OK);
	}
}
