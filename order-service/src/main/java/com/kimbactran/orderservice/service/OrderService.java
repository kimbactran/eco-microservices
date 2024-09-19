package com.kimbactran.orderservice.service;

import com.kimbactran.orderservice.dto.OrderLineItemDto;
import com.kimbactran.orderservice.dto.OrderReq;
import com.kimbactran.orderservice.model.Order;
import com.kimbactran.orderservice.model.OrderLineItem;
import com.kimbactran.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderReq orderReq) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderReq.getOrderLineItemDtoList().stream().map(this::mapToOrderLineItem).collect(Collectors.toList()))
                .build();
        orderRepository.save(order);
        log.info("Placed order: {}", order.getOrderNumber());
    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
        return OrderLineItem.builder()
                .skuCode(orderLineItemDto.getSkuCode())
                .quantity(orderLineItemDto.getQuantity())
                .price(orderLineItemDto.getPrice())
                .build();
    }
}
