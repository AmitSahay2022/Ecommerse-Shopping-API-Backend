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

import com.sahay.ecombackendapi.dto.Cart;
import com.sahay.ecombackendapi.entity.CartItem;
import com.sahay.ecombackendapi.service.CartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
	private CartService cartService;
	@PostMapping("/add/{userId}/{productId}/{qty}")
	public ResponseEntity<String> addToCart(@PathVariable Integer userId,@PathVariable Integer productId,@PathVariable Integer qty){
		return new ResponseEntity<String>(cartService.addToCart(userId, productId, qty),HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{userId}/{productId}")
	public ResponseEntity<String> removeFromCart(@PathVariable Integer userId,@PathVariable Integer productId){
		return new ResponseEntity<String>(cartService.removeFromCart(userId, productId),HttpStatus.OK);
	}
	@DeleteMapping("/delete-all/{userId}")
	public ResponseEntity<String> emptyCart(@PathVariable Integer userId){
		return new ResponseEntity<String>(cartService.emptyCart(userId),HttpStatus.OK);
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<Cart> viewCart(@PathVariable Integer userId){
		List<CartItem> listOfCarts = cartService.viewCart(userId);
		Cart cart=new Cart();
		cart.setCartItems(listOfCarts);
		double total=0.0;
		for (CartItem cartItem : listOfCarts) {
			total=total+cartItem.getProduct().getPrice()*cartItem.getQuantity();
		}
		cart.setTotal(total);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
}
