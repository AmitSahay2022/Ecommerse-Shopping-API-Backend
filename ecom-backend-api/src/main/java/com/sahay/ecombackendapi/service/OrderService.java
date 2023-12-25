package com.sahay.ecombackendapi.service;

import java.util.List;

import com.sahay.ecombackendapi.entity.Order;

public interface OrderService {
	String placeOrder(Order order,Integer userId);

	String cancelOrder(Integer orderId, Integer userId);

	Order viewOrder(Integer orderId);

	String updateOrder(Integer orderId); // This method is for Admin only to update order status

	List<Order> viewAllOrder(); // This method is for Admin only

	List<Order> viewOrderByCustomer(Integer userId);
	
	String deleteOrder(Integer orderId);
}
