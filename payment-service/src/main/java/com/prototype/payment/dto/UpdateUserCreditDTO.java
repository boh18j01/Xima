package com.prototype.payment.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserCreditDTO {
    private String cardNumber;
    private double amount;
}
