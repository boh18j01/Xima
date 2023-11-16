package com.prototype.shopping.cart.service;

import com.prototype.shopping.cart.dto.AddToCartDto;
import com.prototype.shopping.cart.entity.LineItem;
import com.prototype.shopping.cart.entity.ShoppingCart;
import com.prototype.shopping.cart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    public Mono<ShoppingCart> addToCart(AddToCartDto dto) {
        Mono<ShoppingCart> cart = repository.findShoppingCartByClientId(dto.getClientId());
        //TODO verify from the product-service if the product is available for the asked quantity
        return cart.doOnNext(c -> c.getProducts().add(dto.getLineItem()))
                .flatMap(repository::save);
    }

    public Mono<ShoppingCart> addCart(ShoppingCart dto) {
        return repository.save(dto);
    }

    public Flux<ShoppingCart> getAllCarts() {
        return repository.findAll();
    }
}
