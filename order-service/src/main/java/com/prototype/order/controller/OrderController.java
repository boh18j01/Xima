package com.prototype.order.controller;

import com.prototype.order.dto.*;
import com.prototype.order.entity.Order;
import com.prototype.order.service.OrderService;
import com.prototype.order.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    private static final Logger LOGGER = LoggerFactory.getLogger( OrderController.class.getName() );




    @PostMapping("/new")
    public Mono<OrderDto> createNewOrder(@RequestBody CreateOrderRequest request) {
        Order order = AppUtils.createOder(request);
        return this.service.createOrder(order).map(AppUtils::orderToDto) ;

    }

    @PostMapping("/payment")
    public Mono<OrderDto> payForOrder(@RequestBody MakePaymentRequest request) {
        return this.service.payForOrder(request).map(AppUtils::orderToDto) ;
    }

    @GetMapping("/{orderId}")
    public Mono<OrderDto> getOrderTest(@PathVariable String orderId){
        return this.service.getOrderById(orderId).map(AppUtils::orderToDto);
    }


    @GetMapping("/client/{clientId}")
    public Flux<OrderDto> getClientOrders(@PathVariable String clientId){
        return this.service.getClientOrders(clientId).map(AppUtils::orderToDto);
    }

    @GetMapping
    public Flux<OrderDto> getOrders(){
        return service.getAllOrders();
    }



//    @PostMapping
//    public Mono<OrderDto> palceOrder(@RequestBody OrderDto dto) {
//        return service.placeOrder(dto);
//    }

}
