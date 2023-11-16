package com.prototype.user.dto;

import lombok.Data;

@Data
public class UpdateUserCredit {
    private String cardNumber;
    private double amount;
}
