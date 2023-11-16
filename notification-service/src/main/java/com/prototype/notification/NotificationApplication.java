package com.prototype.notification;

import com.ecommerce.core.models.Order.OrderPlaceEvents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationApplication.class);
    @KafkaListener(id="notificationGroup", topics = "notification")
    public void onEvent(OrderPlaceEvents p) {
        LOGGER.info("Received: {}", p.getOrderNumber());

    }
}


