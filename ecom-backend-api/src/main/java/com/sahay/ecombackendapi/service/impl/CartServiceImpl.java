package com.sahay.ecombackendapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sahay.ecombackendapi.entity.CartItem;
import com.sahay.ecombackendapi.entity.Product;
import com.sahay.ecombackendapi.entity.User;
import com.sahay.ecombackendapi.exception.CartItemNotFoundException;
import com.sahay.ecombackendapi.repository.CartItemRepository;
import com.sahay.ecombackendapi.service.CartService;
import com.sahay.ecombackendapi.service.ProductService;
import com.sahay.ecombackendapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartItemRepository cartItemRepository;
    private UserService userService;
    private ProductService productService;
	@Override
	public String addToCart(Integer userId, Integer productId,Integer qty) {
		User user = userService.getUser(userId);
		Product product = productService.getProductById(productId);
		Optional<CartItem> cart = cartItemRepository.findByUserIdAndProductId(userId, productId);
		if(cart.isPresent()) {
			CartItem cartItem = cart.get();
			cartItem.setQuantity(qty);
			cartItemRepository.save(cartItem);
		}else {
			CartItem item=new CartItem();
			item.setProduct(product);
			item.setUser(user);
			item.setQuantity(qty);
			cartItemRepository.save(item);
		}
		return "Product Added To Cart";
	}

	@Override
	public String removeFromCart(Integer userId, Integer productId) {
		CartItem cartItem = cartItemRepository
		       .findByUserIdAndProductId(userId, productId)
		       .orElseThrow(()->new CartItemNotFoundException("Cart Item Not Found"));
		cartItemRepository.delete(cartItem);
		return "Product Removed From Cart Successfully";
	}

	@Override
	public List<CartItem> viewCart(Integer userId) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByUserId(userId);
	}

	@Override
	public String emptyCart(Integer userId) {
		// TODO Auto-generated method stub
		List<CartItem> viewCart = viewCart(userId);
		cartItemRepository.deleteAll(viewCart);
		return "Cart Is Empty";
	}

}
