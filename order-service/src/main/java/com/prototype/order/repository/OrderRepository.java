package com.prototype.order.repository;

import com.prototype.order.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Flux<Order> findAllByClientIdEquals(String clientID);
}
