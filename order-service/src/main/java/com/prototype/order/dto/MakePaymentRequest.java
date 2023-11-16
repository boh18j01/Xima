package com.prototype.order.dto;


import lombok.Data;

@Data
public class MakePaymentRequest {
    private String orderId;
    private String cardnumber;
}
