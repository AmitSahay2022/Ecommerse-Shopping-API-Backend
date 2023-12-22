package com.sahay.ecombackendapi.service;

import java.util.List;

import com.sahay.ecombackendapi.entity.Category;

public interface CategoryService {
	String addCategory(Category category);

	String updateCategory(Integer categoryId, Category category);

	String deleteCategory(Integer id);

	Category getCategoryById(Integer id);

	List<Category> getAllCategory();
}
