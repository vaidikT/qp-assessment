package com.grocery.booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    @NotNull(message = "Grocery item ID cannot be null")
    private Long groceryItemId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    
}
