package com.sahay.ecombackendapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahay.ecombackendapi.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
  List<CartItem> findByUserId(Integer userId);
  Optional<CartItem> findByUserIdAndProductId(Integer userId,Integer productId);
}
