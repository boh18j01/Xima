package com.ecommerce.core.models.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcess {
    private String clientId;
    private String cardNumber;
    private double amount;
}
