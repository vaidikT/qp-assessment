package com.grocery.booking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.booking.dto.OrderDTO;
import com.grocery.booking.dto.OrderItemDTO;
import com.grocery.booking.entity.GroceryItem;
import com.grocery.booking.entity.Order;
import com.grocery.booking.entity.OrderItem;
import com.grocery.booking.entity.User;
import com.grocery.booking.repository.GroceryItemRepository;
import com.grocery.booking.repository.OrderRepository;
import com.grocery.booking.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private UserRepository userRepository;

    public void placeOrder(OrderDTO orderDTO) {

    	User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus("PLACED");
        order.setDate(new Date());
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            GroceryItem groceryItem = groceryItemRepository.findById(itemDTO.getGroceryItemId())
                    .orElseThrow(() -> new IllegalArgumentException("Grocery item not found"));
            orderItem.setGroceryItem(groceryItem);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItems.add(orderItem);

            int newInventoryQuantity = groceryItem.getInventoryQuantity() - itemDTO.getQuantity();
            if (newInventoryQuantity < 0) {
                throw new IllegalStateException("Insufficient inventory for item: " + groceryItem.getName());
            }
            groceryItem.setInventoryQuantity(newInventoryQuantity);
            groceryItemRepository.save(groceryItem);
        }
        order.setOrderItems(orderItems);

        orderRepository.save(order);
    }
}
