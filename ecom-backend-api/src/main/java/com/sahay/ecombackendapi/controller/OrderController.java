package com.sahay.ecombackendapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahay.ecombackendapi.entity.Order;
import com.sahay.ecombackendapi.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
   private OrderService orderService;
   @PostMapping("{userId}")
   public ResponseEntity<String> placeOrder(@RequestBody Order order,@PathVariable Integer userId){
	   return new ResponseEntity<String>(orderService.placeOrder(order, userId),HttpStatus.CREATED);
   }
   @DeleteMapping("{orderId}/{userId}")
   public ResponseEntity<String> cancelOrder(@PathVariable Integer orderId,@PathVariable Integer userId){
	   return new ResponseEntity<String>(orderService.cancelOrder(orderId, userId),HttpStatus.OK);
   }
   @GetMapping("view/{userId}")
   public ResponseEntity<List<Order>> viewOrderByCustomer(@PathVariable Integer userId){
	   return new ResponseEntity<List<Order>>(orderService.viewOrderByCustomer(userId),HttpStatus.OK);
   }
   @GetMapping("view-all")
   public ResponseEntity<List<Order>> viewAllOrder(){
	   return new ResponseEntity<List<Order>>(orderService.viewAllOrder(),HttpStatus.OK);
   }
   @DeleteMapping("delete/{orderId}")
   public ResponseEntity<String> deleteOrder(@PathVariable Integer orderId){
	   return new ResponseEntity<String>(orderService.deleteOrder(orderId),HttpStatus.OK);
   }
}
