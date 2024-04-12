package com.grocery.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemDTO {

    private Long id;

	@NotBlank(message = "Name cannot be blank")
    private String name;

    @PositiveOrZero(message = "Price must be a positive or zero value")
    private double price;

    @PositiveOrZero(message = "Inventory quantity must be a positive or zero value")
    private int inventoryQuantity;

    @PositiveOrZero(message = "Max inventory quantity must be a positive or zero value")
    private Integer maxInventoryQuantity;
    
}
