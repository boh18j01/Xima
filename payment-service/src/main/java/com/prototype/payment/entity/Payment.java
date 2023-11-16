package com.prototype.payment.entity;


import com.ecommerce.core.models.payments.PaymentStatus;
import com.prototype.payment.dto.PaymentType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private String clientId;
    private String cardNumber;
    private String transactionNr;
    private double amount;
    private PaymentStatus status;
    private PaymentType paymentType;
}
