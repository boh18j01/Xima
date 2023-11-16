package com.prototype.payment.service;


import com.ecommerce.core.models.payments.PaymentAcceptanceOrRejection;
import com.ecommerce.core.models.payments.PaymentProcess;
import com.ecommerce.core.models.payments.PaymentRefund;
import com.ecommerce.core.models.payments.PaymentStatus;
import com.prototype.payment.dto.*;
import com.prototype.payment.entity.Payment;
import com.prototype.payment.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PaymentService {

    private static final String SOURCE = "payment";
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private  PaymentRepository paymentRepository;





    public Mono<Payment> savePayment(Payment dtoMono) {
        return paymentRepository.insert(dtoMono);
    }

    public Mono<Payment> updatePayment(Payment dtoMono) {
        return paymentRepository.save(dtoMono);
    }



    public  Mono<Payment> getPaymentById(String id){
        return this.paymentRepository.findById(id);
    }





}