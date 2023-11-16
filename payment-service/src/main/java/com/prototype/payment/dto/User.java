package com.prototype.payment.dto;

import lombok.Data;

@Data
public class User {
    private String cardNumber;
    private Double availableCredit;
    private Double creditLimit;
}
