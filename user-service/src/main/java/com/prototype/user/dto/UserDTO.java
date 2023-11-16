package com.prototype.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String cardNumber;
    private Double availableCredit;
    private Double creditLimit;
}
