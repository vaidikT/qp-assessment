package com.grocery.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.grocery.booking.dto.GroceryItemDTO;
import com.grocery.booking.dto.OrderDTO;
import com.grocery.booking.service.OrderService;
import com.grocery.booking.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/grocery-items")
	public List<GroceryItemDTO> viewAvailableGroceryItems() {
		return userService.viewAvailableGroceryItems();
	}

	@PostMapping("/orders")
	public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder("Validation failed:");
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(" ->").append(error.getDefaultMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
		}
		try {
			orderService.placeOrder(orderDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error : " + e.getMessage());
		}
	}
}
