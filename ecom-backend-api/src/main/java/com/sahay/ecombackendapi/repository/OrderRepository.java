package com.sahay.ecombackendapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahay.ecombackendapi.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
  Optional<Order> findByIdAndUserId(Integer orderId,Integer userId);
  List<Order> findByUserId(Integer userId);
}
