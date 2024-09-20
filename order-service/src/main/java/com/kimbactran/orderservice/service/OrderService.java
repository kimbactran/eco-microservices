package com.kimbactran.orderservice.service;

import com.kimbactran.orderservice.dto.InventoryRes;
import com.kimbactran.orderservice.dto.OrderLineItemDto;
import com.kimbactran.orderservice.dto.OrderReq;
import com.kimbactran.orderservice.model.Order;
import com.kimbactran.orderservice.model.OrderLineItem;
import com.kimbactran.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;

    public void placeOrder(OrderReq orderReq) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderReq.getOrderLineItemDtoList().stream().map(this::mapToOrderLineItem).collect(Collectors.toList()))
                .build();
        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItem::getSkuCode).collect(Collectors.toList());
        // Check inventory service if item in stock
        InventoryRes[] inventoryResArray = webClient.build().get()
                .uri("http://inventory-service/api/inventory/isInStock", uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryRes[].class)
                                        .block();
        assert inventoryResArray != null;
        boolean allProductInStock = Arrays.stream(inventoryResArray).allMatch(InventoryRes::isInStock);
        if(allProductInStock) {
            orderRepository.save(order);
            log.info("Placed order: {}", order.getOrderNumber());
        } else {
            throw new IllegalArgumentException("Product is not in stock");
        }

    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
        return OrderLineItem.builder()
                .skuCode(orderLineItemDto.getSkuCode())
                .quantity(orderLineItemDto.getQuantity())
                .price(orderLineItemDto.getPrice())
                .build();
    }
}
