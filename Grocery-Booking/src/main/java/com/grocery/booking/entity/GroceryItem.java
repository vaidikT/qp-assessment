package com.grocery.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grocery_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(name = "inventory_quantity", nullable = false)
    private int inventoryQuantity;

    @Column(name = "max_inventory_quantity")
    private Integer maxInventoryQuantity;
    
    
}

