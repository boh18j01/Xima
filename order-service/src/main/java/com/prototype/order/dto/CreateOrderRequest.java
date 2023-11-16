package com.prototype.order.dto;


import com.ecommerce.core.models.products.ProductLine;
import lombok.Data;
import java.util.List;


@Data
public class CreateOrderRequest {
    private String clientId;
    private String shipTo;
    private Double totalPrice;
    private List<ProductLine> products;

}
