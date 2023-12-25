package com.sahay.ecombackendapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahay.ecombackendapi.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
  Optional<OrderItem> findByOrderId(Integer orderId);
  List<OrderItem> findByOrderUserId(Integer userId);
}
