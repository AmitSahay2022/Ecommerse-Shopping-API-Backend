package com.sahay.ecombackendapi.dto;

import java.util.ArrayList;
import java.util.List;

import com.sahay.ecombackendapi.entity.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
	private List<CartItem> cartItems = new ArrayList<>();
	private Double total;
}
