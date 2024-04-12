package com.grocery.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.booking.dto.GroceryItemDTO;
import com.grocery.booking.entity.GroceryItem;
import com.grocery.booking.repository.GroceryItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {

 @Autowired
 private GroceryItemRepository groceryItemRepository;

 public void addGroceryItem(GroceryItemDTO groceryItemDTO) {
     GroceryItem groceryItem = new GroceryItem();
     groceryItem.setName(groceryItemDTO.getName());
     groceryItem.setPrice(groceryItemDTO.getPrice());
     groceryItem.setInventoryQuantity(groceryItemDTO.getInventoryQuantity());
     groceryItem.setMaxInventoryQuantity(groceryItemDTO.getMaxInventoryQuantity());
     groceryItemRepository.save(groceryItem);
 }

 public List<GroceryItemDTO> viewGroceryItems() {
     List<GroceryItem> groceryItems = groceryItemRepository.findAll();
     return groceryItems.stream()
             .map(this::convertToDTO)
             .collect(Collectors.toList());
 }

 public void removeGroceryItem(Long itemId) {
     groceryItemRepository.deleteById(itemId);
 }

 public void updateGroceryItem(Long itemId, GroceryItemDTO groceryItemDTO) {
     Optional<GroceryItem> optionalGroceryItem = groceryItemRepository.findById(itemId);
     if (optionalGroceryItem.isPresent()) {
         GroceryItem groceryItem = optionalGroceryItem.get();
         groceryItem.setName(groceryItemDTO.getName());
         groceryItem.setPrice(groceryItemDTO.getPrice());
         groceryItem.setInventoryQuantity(groceryItemDTO.getInventoryQuantity());
         groceryItem.setMaxInventoryQuantity(groceryItemDTO.getMaxInventoryQuantity());
         groceryItemRepository.save(groceryItem);
     } else {
         throw new EntityNotFoundException("Grocery item with ID " + itemId + " not found");
     }
 }

 public void manageInventory(Long itemId, int quantity) {
     Optional<GroceryItem> optionalGroceryItem = groceryItemRepository.findById(itemId);
     if (optionalGroceryItem.isPresent()) {
         GroceryItem groceryItem = optionalGroceryItem.get();
         groceryItem.setInventoryQuantity(quantity);
         groceryItemRepository.save(groceryItem);
     } else {
         throw new EntityNotFoundException("Grocery item with ID " + itemId + " not found");
     }
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

