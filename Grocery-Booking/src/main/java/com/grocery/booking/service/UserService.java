package com.grocery.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.booking.dto.GroceryItemDTO;
import com.grocery.booking.entity.GroceryItem;
import com.grocery.booking.repository.GroceryItemRepository;

@Service
public class UserService {

 @Autowired
 private GroceryItemRepository groceryItemRepository;

 public List<GroceryItemDTO> viewAvailableGroceryItems() {
     List<GroceryItem> groceryItems = groceryItemRepository.findAll();
     return groceryItems.stream()
             .map(this::convertToDTO)
             .collect(Collectors.toList());
 }

 private GroceryItemDTO convertToDTO(GroceryItem groceryItem) {
     GroceryItemDTO dto = new GroceryItemDTO();
     dto.setId(groceryItem.getId());
     dto.setName(groceryItem.getName());
     dto.setPrice(groceryItem.getPrice());
     dto.setInventoryQuantity(groceryItem.getInventoryQuantity());
     dto.setMaxInventoryQuantity(groceryItem.getMaxInventoryQuantity());
     return dto;
 }
}
