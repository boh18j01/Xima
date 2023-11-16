package com.ecommerce.core.models.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRefund {
    private String clientId;
    private String cardNumber;
    private String transactionNr;
    private double amount;
}
