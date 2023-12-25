package com.sahay.ecombackendapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sahay.ecombackendapi.entity.CartItem;
import com.sahay.ecombackendapi.entity.Order;
import com.sahay.ecombackendapi.entity.OrderItem;
import com.sahay.ecombackendapi.entity.User;
import com.sahay.ecombackendapi.exception.EmptyCartException;
import com.sahay.ecombackendapi.exception.OrderNotFoundException;
import com.sahay.ecombackendapi.repository.OrderItemRepository;
import com.sahay.ecombackendapi.repository.OrderRepository;
import com.sahay.ecombackendapi.service.CartService;
import com.sahay.ecombackendapi.service.OrderService;
import com.sahay.ecombackendapi.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository itemRepository;
    private UserService userService;
    private CartService cartService;
	@Override
	public String placeOrder(Order oder,Integer userId) {
		// TODO Auto-generated method stub
		User user = userService.getUser(userId);
		Integer usrId=user.getId();
		List<CartItem> listOfCartItems = cartService.viewCart(usrId);
		if(listOfCartItems.size()==0) {
			throw new EmptyCartException("Can not Order because Cart is Empty");
		}
		Order order=new Order();
		order.setUser(user);
		order.setMobile(oder.getMobile());
		order.setDeleveryAddress(oder.getDeleveryAddress());
		
		double total=0.0;
		for(CartItem i:listOfCartItems) {
			OrderItem item=new OrderItem();
			item.setProduct(i.getProduct());
			item.setQty(i.getQuantity());
			double subtotal=i.getProduct().getPrice()*i.getQuantity();
			item.setSubTotal(subtotal);
			total+=subtotal;
			item.setOrder(order);
			order.getItem().add(item);
		}
		order.setTotalPrice(total);
		Order saved = orderRepository.save(order);
		if(saved !=null) {
			cartService.emptyCart(userId);
			return "Order Placed Successfully";
		}
		return "Something went WRONG!";
	}

	@Override
	public String cancelOrder(Integer orderId, Integer userId) {
		// TODO Auto-generated method stub
		Order order = orderRepository
		           .findByIdAndUserId(orderId, userId)
		           .orElseThrow(()->new OrderNotFoundException("Order Not Found!"));
		orderRepository.delete(order);
		return "Order Cancled Successfully";
	}

	@Override
	public Order viewOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order Not Found!"));
		return order;
	}

	//This Method is Not Implemented 
	@Override
	public String updateOrder(Integer orderId) {
		Order order = viewOrder(orderId);
		return null;
	}

	@Override
	public List<Order> viewAllOrder() {
		// TODO Auto-generated method stub
		
		return orderRepository.findAll();
	}

	@Override
	public List<Order> viewOrderByCustomer(Integer userId) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserId(userId);
	}
	
	@Override
	public String deleteOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Order viewOrder = viewOrder(orderId);
		orderRepository.delete(viewOrder);
		return "Order Deleted Successfully";
	}

}
