package com.sahay.ecombackendapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahay.ecombackendapi.entity.Product;
import com.sahay.ecombackendapi.entity.User;
import com.sahay.ecombackendapi.entity.WishList;
import com.sahay.ecombackendapi.repository.WishlistRepository;
import com.sahay.ecombackendapi.service.ProductService;
import com.sahay.ecombackendapi.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@AllArgsConstructor
public class WishListController {
  private WishlistRepository repository;
  private UserService userService;
  private ProductService productService;
  @PostMapping("{userId}/{productId}")
  public ResponseEntity<String> addToWishList(@PathVariable Integer userId,@PathVariable Integer productId){
	  WishList wishList=new WishList();
	  User user = userService.getUser(userId);
	  Product product = productService.getProductById(productId);
	  wishList.setUser(user);
	  wishList.setProduct(product);
	  repository.save(wishList);
	  return new ResponseEntity<String>("Product added to WishList",HttpStatus.CREATED);
  }
  @DeleteMapping("{userId}/{productId}")
  public ResponseEntity<String> removeFromWishList(@PathVariable Integer userId,@PathVariable Integer productId){
	  WishList wishList = repository
			                 .findByUserIdAndProductId(userId, productId)
			                 .orElseThrow(()->new RuntimeException("WishList not Found!"));
	  repository.delete(wishList);
	  return new ResponseEntity<String>("Product Removed from WishList",HttpStatus.OK);
  }
  @GetMapping("{userId}")
  public ResponseEntity<List<WishList>> viewWishList(@PathVariable Integer userId){
	  return new ResponseEntity<List<WishList>>(repository.findByUserId(userId),HttpStatus.OK);
  }
}
