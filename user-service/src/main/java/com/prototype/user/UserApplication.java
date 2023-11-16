package com.prototype.user;

import com.ecommerce.core.models.payments.MakePaymentCommand;
import com.prototype.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApplication.class);


    @Autowired
    UserService userService;

    @KafkaListener(id = "userPayment", topics = "payment-process-user")
    public void onEvent(MakePaymentCommand command) {
        LOGGER.info("Received: {}" , command);
        userService.processPayment(command);
    }





}
