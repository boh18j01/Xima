package com.prototype.shopping.cart.repository;


import com.prototype.shopping.cart.entity.ShoppingCart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ShoppingCartRepository extends ReactiveMongoRepository<ShoppingCart, String> {

    Mono<ShoppingCart> findShoppingCartByClientId(String clientId);
}
