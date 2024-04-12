package com.grocery.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.grocery.booking.dto.GroceryItemDTO;
import com.grocery.booking.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/grocery-items")
public class AdminController {

	@Autowired
	private AdminService adminGroceryItemService;

	@PostMapping
	public ResponseEntity<String> addGroceryItem(@Valid @RequestBody GroceryItemDTO groceryItemDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder("Validation failed:");
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(" ->").append(error.getDefaultMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
		}
		try {
			adminGroceryItemService.addGroceryItem(groceryItemDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Grocery item added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error : " + e.getMessage());
		}
	}

	@GetMapping
	public List<GroceryItemDTO> viewGroceryItems() {
		return adminGroceryItemService.viewGroceryItems();
	}

	@DeleteMapping("/{itemId}")
	public void removeGroceryItem(@PathVariable Long itemId) {
		adminGroceryItemService.removeGroceryItem(itemId);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<String> updateGroceryItem(@PathVariable Long itemId,
			@Valid @RequestBody GroceryItemDTO groceryItemDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder("Validation failed:");
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(" ->").append(error.getDefaultMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
		}
		try {
			adminGroceryItemService.updateGroceryItem(itemId, groceryItemDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Grocery item updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error : " + e.getMessage());
		}
	}

	@PutMapping("/{itemId}/inventory")
	public ResponseEntity<String> manageInventory(@PathVariable Long itemId, @RequestParam int quantity) {
		try {
			adminGroceryItemService.manageInventory(itemId, quantity);
			return ResponseEntity.status(HttpStatus.CREATED).body("Inventory updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error : " + e.getMessage());
		}
	}
}
