package com.ecommerce.core.models.payments;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessedEvent {
    private String paymentId;
    private String cardnumber;
    private PaymentStatus status;
}
