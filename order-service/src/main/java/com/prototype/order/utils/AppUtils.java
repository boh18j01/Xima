package com.prototype.order.utils;

import com.ecommerce.core.models.Order.OrderStatus;
import com.prototype.order.dto.CreateOrderRequest;
import com.prototype.order.dto.OrderDto;
import com.prototype.order.entity.Order;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppUtils {

    static public OrderDto orderToDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .products(order.getProducts())
                .clientId(order.getClientId())
                .address(order.getShipTo())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    static  public Order createOder(CreateOrderRequest request){
        return Order.builder()
                .id(UUID.randomUUID().toString())
                .clientId(request.getClientId())
                .orderedAt(LocalDateTime.now())
                .products(request.getProducts())
                .status(OrderStatus.NEW)
                .totalPrice(request.getTotalPrice())
                .shipTo(request.getShipTo())
                .build();
    }


}
