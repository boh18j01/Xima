package com.prototype.order.dto;

import lombok.Data;

@Data
public class PayForOrderRequest {
    private String orderId;
    private String cardNumber;
}
