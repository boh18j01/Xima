package com.prototype.order;

import com.ecommerce.core.models.payments.PaymentAcceptanceOrRejection;
import com.ecommerce.core.models.products.ProductStockStatus;
import com.prototype.order.service.OrderManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApplication.class);




    @Autowired
    OrderManageService orderManageService;

   @KafkaListener(id = "orderGroup1", topics = "payment-process-response")
     public void onEvent(PaymentAcceptanceOrRejection p) {
      LOGGER.info("Received: {}" , p);
       orderManageService.processOrder(p);
     }

    @KafkaListener(id = "orderGroup2", topics = "product-process-response")
    public void onEvent(ProductStockStatus p) {
        LOGGER.info("Received: {}" , p);
        System.err.println("Product Availability Response");
        orderManageService.processProductStock(p);
    }


}
