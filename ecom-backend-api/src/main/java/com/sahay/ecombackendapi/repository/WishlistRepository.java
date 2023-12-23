package com.sahay.ecombackendapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahay.ecombackendapi.entity.WishList;

public interface WishlistRepository extends JpaRepository<WishList, Long>{
  List<WishList> findByUserId(Integer id);
  Optional<WishList> findByUserIdAndProductId(Integer userId,Integer productId);
}
