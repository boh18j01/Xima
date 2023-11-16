package com.ecommerce.core.models.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAcceptanceOrRejection {
    private String clientId;
    private String transactionNr;
    private PaymentStatus status;
}
