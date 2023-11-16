package com.prototype.user.repository;

import com.prototype.user.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User>  findByCardnumberEquals(String cardNumber);
}
