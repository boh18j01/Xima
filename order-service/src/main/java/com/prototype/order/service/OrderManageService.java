package com.prototype.order.service;

import com.ecommerce.core.models.Order.OrderPlaceEvents;
import com.ecommerce.core.models.Order.OrderStatus;
import com.ecommerce.core.models.payments.PaymentAcceptanceOrRejection;
import com.ecommerce.core.models.payments.PaymentStatus;
import com.ecommerce.core.models.products.ProductLine;
import com.ecommerce.core.models.products.ProductStockStatus;
import com.prototype.order.dto.*;
import com.prototype.order.entity.Order;
/*import com.prototype.order.dto.OrderPlaceEvents;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderManageService {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    OrderService orderService;





    public void processOrder(PaymentAcceptanceOrRejection p) {
        System.err.println("PaymentAcceptanceOrRejection: " + p.toString());
        Order order = this.orderService.getOrderById(p.getClientId()).block();
        if (p.getStatus().equals(PaymentStatus.CONFIRMED)) {
            order.setStatus(OrderStatus.PAYMENT_CONFIRMED);
        } else if (p.getStatus().equals(PaymentStatus.REJECTED)) {
            order.setStatus(OrderStatus.PAYMENT_REJECTED);
        }
        this.orderService.updateOrder(order).subscribe();

    }

    public void placeOrder(OrderPlaceEvents p){
        if(p.getStatus().equals(OrderStatus.PAYMENT_CONFIRMED)){
        kafkaTemplate.send("notification",new OrderPlaceEvents(p.getOrderNumber(),OrderStatus.PAYMENT_CONFIRMED));
} }


    public void processProductStock(ProductStockStatus p) {
        System.err.println("Start Processing Product Response " + p.getOrderId());
        Order order =  this.orderService.getOrderById(p.getOrderId()).block();
        Optional<ProductLine> unavailableProduct = p.getProducts().stream().filter(pr -> !pr.isAvailable()).findFirst();
        if(unavailableProduct.isPresent()){
            order.setStatus(OrderStatus.PRODUCT_NOT_AVAILABLE);

        }else{
            order.setStatus(OrderStatus.RESERVED);
        }
        this.orderService.updateOrder(order).subscribe();
    }
}