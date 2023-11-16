package com.prototype.order.dto;

import com.ecommerce.core.models.Order.OrderStatus;
import com.ecommerce.core.models.products.ProductLine;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {
    private String id;
    private String clientId;
    private String address;
    private Double totalPrice;
    private List<ProductLine> products;
    private OrderStatus status;
}
