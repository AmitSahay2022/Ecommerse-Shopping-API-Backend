package com.sahay.ecombackendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahay.ecombackendapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
