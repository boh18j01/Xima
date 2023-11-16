package com.prototype.payment.service;


import com.ecommerce.core.models.payments.*;
import com.prototype.payment.dto.PaymentType;
import com.prototype.payment.dto.UpdateUserCreditDTO;
import com.prototype.payment.dto.User;
import com.prototype.payment.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PaymentProcessManager {




    private final PaymentService paymentService;
    private KafkaTemplate<String, Object> template;


    public PaymentProcessManager(KafkaTemplate<String, Object> template,
                           PaymentService paymentService) {
        this.template = template;
        this.paymentService = paymentService;
    }



    public void processPayment(PaymentProcess p) {
        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .cardNumber(p.getCardNumber())
                .clientId(p.getClientId())
                .transactionNr(UUID.randomUUID().toString())
                .amount(p.getAmount())
                .paymentType(PaymentType.PURCHASE)
                .status(PaymentStatus.PROCESSING)
                .build();

        this.template.send("payment-process-user", new MakePaymentCommand(payment.getId(),p.getCardNumber(),p.getAmount()));
        this.paymentService.savePayment(payment).subscribe();

    }




    public void resolvePaymentProcess(PaymentProcessedEvent event) {

        this.paymentService.getPaymentById(event.getPaymentId()).subscribe(payment -> {
            PaymentAcceptanceOrRejection paymentAcceptanceOrRejection = new PaymentAcceptanceOrRejection();
        paymentAcceptanceOrRejection.setClientId(payment.getClientId());
        paymentAcceptanceOrRejection.setTransactionNr(String.valueOf(UUID.randomUUID().getLeastSignificantBits()));
        paymentAcceptanceOrRejection.setStatus(PaymentStatus.PROCESSING);

        if(event.getStatus().equals(PaymentStatus.APPROVED)){
                payment.setStatus(PaymentStatus.CONFIRMED);
                paymentAcceptanceOrRejection.setStatus(PaymentStatus.CONFIRMED);
        }else if(event.getStatus().equals(PaymentStatus.REJECTED)){
            payment.setStatus(PaymentStatus.REJECTED);
            paymentAcceptanceOrRejection.setStatus(PaymentStatus.REJECTED);
        }

        this.template.send("payment-process-response", paymentAcceptanceOrRejection);
        this.paymentService.updatePayment(payment).subscribe();
        });

    }
}
