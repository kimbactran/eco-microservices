package com.kimbactran.orderservice.dto;

import com.kimbactran.orderservice.model.OrderLineItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {
    private List<OrderLineItemDto> orderLineItemDtoList;
}
