package com.sahay.ecombackendapi.service;

import java.util.List;

import com.sahay.ecombackendapi.entity.CartItem;

public interface CartService {
	String addToCart(Integer userId, Integer productId,Integer qty);

	String removeFromCart(Integer userId, Integer productId);

	List<CartItem> viewCart(Integer userId);

	String emptyCart(Integer userId);
}
