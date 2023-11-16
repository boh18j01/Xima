package com.prototype.payment.repository;

import com.prototype.payment.entity.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {

    Mono<Payment> findByClientIdEquals(String clientId);
}
