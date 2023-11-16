package com.prototype.payment;


import com.ecommerce.core.models.payments.PaymentProcess;
import com.ecommerce.core.models.payments.PaymentProcessedEvent;
import com.ecommerce.core.models.payments.PaymentRefund;
import com.prototype.payment.service.PaymentProcessManager;
import com.prototype.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
//@ComponentScan(basePackages = {"com.prototype.user","com.prototype.order","com.prototype.payment"})
public class PaymentApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Autowired
    PaymentProcessManager paymentProcessManager;

    @KafkaListener(id = "paymentGroup", topics = "payment-process")
    public void onEvent(PaymentProcess p) {
        LOGGER.info("Received: {}" , p);
        paymentProcessManager.processPayment(p);
    }


    @KafkaListener(id = "paymentUserGroup", topics = "payment-process-user-response")
    public void onEvent(PaymentProcessedEvent event) {
        LOGGER.info("Received: {}" , event);
        paymentProcessManager.resolvePaymentProcess(event);
    }





}