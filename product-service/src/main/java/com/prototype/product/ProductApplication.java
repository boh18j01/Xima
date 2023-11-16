package com.prototype.product;

import com.ecommerce.core.models.products.CheckProductStock;
import com.prototype.product.service.ProductOrderManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
@EnableKafka
//@ComponentScan(basePackages = {"com.prototype.order","com.prototype.product"})
public class ProductApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Autowired
    ProductOrderManageService productManageService;

    @KafkaListener(id = "productGroup", topics = "product-process")
    public void onEvent(CheckProductStock checkProductStock) {
        LOGGER.info("Received: {}" , checkProductStock);
        productManageService.checkAndReserveProduct(checkProductStock);
    }
}
