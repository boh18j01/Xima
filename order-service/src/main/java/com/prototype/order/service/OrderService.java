package com.prototype.order.service;

import com.ecommerce.core.models.Order.OrderStatus;
import com.ecommerce.core.models.payments.PaymentProcess;
import com.ecommerce.core.models.products.CheckProductStock;
import com.prototype.order.dto.MakePaymentRequest;
import com.prototype.order.dto.OrderDto;
import com.prototype.order.entity.Order;
import com.prototype.order.repository.OrderRepository;
import com.prototype.order.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;


    public Flux<OrderDto> getAllOrders() {
        return repository.findAll().map(AppUtils::orderToDto);
    }

    public Mono<Order> getOrderById(String orderID){
        return repository.findById(orderID);
    }

    public Mono<Order> updateOrder(Order o){
        return repository.save(o);
    }

    public Mono<Order> createOrder(Order o) {
        this.kafkaTemplate.send("product-process", CheckProductStock.builder()
                .orderId(o.getId())
                .products(o.getProducts()).build());
        return repository.insert(o);
    }


    public Flux<Order> getClientOrders(String clientId){
        return this.repository.findAllByClientIdEquals(clientId);
    }


    public Mono<Order> payForOrder(MakePaymentRequest request) {
        return repository.findById(request.getOrderId()).flatMap(order -> {
            if(order.getStatus().equals(OrderStatus.RESERVED)){
                order.setStatus(OrderStatus.PAYMENT_PROCESS);
                this.makePayment(AppUtils.orderToDto(order), request.getCardnumber());
            }
            return repository.save(order);
        });
    }

    public void makePayment(OrderDto oder, String cardnumber){

        PaymentProcess processPayment = new  PaymentProcess(oder.getId(),cardnumber,oder.getTotalPrice());

        System.err.println("Send Event to  Payment Service");
        this.kafkaTemplate.send("payment-process", processPayment);
    }
}
