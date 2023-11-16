package com.ecommerce.core.models.Order;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderPlaceEvents {

    private String orderNumber;
    private OrderStatus status;

}
