package com.grocery.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery.booking.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

