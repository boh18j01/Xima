package com.prototype.shopping.cart.controller;

import com.prototype.shopping.cart.dto.AddToCartDto;
import com.prototype.shopping.cart.entity.ShoppingCart;
import com.prototype.shopping.cart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService service;

    @PostMapping("/add")
    public Mono<ShoppingCart> addToCart(@RequestBody AddToCartDto dto) {
        return service.addToCart(dto);
    }

    @PostMapping
    public Mono<ShoppingCart> addCart(@RequestBody ShoppingCart dto) {
        return service.addCart(dto);
    }

    @GetMapping
    public Flux<ShoppingCart> getAll() {
        return service.getAllCarts();
    }
}
