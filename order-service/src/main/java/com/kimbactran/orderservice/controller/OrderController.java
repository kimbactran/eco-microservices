package com.kimbactran.orderservice.controller;

import com.kimbactran.orderservice.dto.OrderReq;
import com.kimbactran.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderReq orderReq) {
        orderService.placeOrder(orderReq);
        return "Order Placed Succesfully!";
    }
}
