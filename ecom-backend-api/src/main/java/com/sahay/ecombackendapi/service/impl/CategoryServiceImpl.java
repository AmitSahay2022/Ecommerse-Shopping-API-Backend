package com.sahay.ecombackendapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sahay.ecombackendapi.entity.Category;
import com.sahay.ecombackendapi.exception.CategoryNotFoundException;
import com.sahay.ecombackendapi.repository.CategoryRepository;
import com.sahay.ecombackendapi.service.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
	@Override
	public String addCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);
		return "Category Saved Successfully";
	}

	@Override
	public String updateCategory(Integer categoryId, Category cat) {
		// TODO Auto-generated method stub
		Category category = getCategoryById(categoryId);
		category.setName(cat.getName());
		category.setDescription(cat.getDescription());
		categoryRepository.save(category);
		return "Updated Successfully";
	}

	@Override
	public String deleteCategory(Integer id) {
		
		Category category = getCategoryById(id);
		categoryRepository.delete(category);
		return "Deleted Successfully";
	}

	@Override
	public Category getCategoryById(Integer id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository
				                .findById(id)
				                .orElseThrow(
				                		()->new CategoryNotFoundException("Category not found"));
		return category;
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
