package com.kimbactran.orderservice.repository;

import com.kimbactran.orderservice.model.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
}
