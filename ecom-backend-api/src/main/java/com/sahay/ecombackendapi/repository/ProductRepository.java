package com.sahay.ecombackendapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahay.ecombackendapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
  List<Product> findByCategoryName(String category);
}
