package com.prototype.user.controller;

import com.prototype.user.dto.UserDTO;
import com.prototype.user.dto.UpdateUserCredit;
import com.prototype.user.dto.CreateUserRequest;
import com.prototype.user.service.UserService;
import com.prototype.user.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public Mono<UserDTO> getUserById(@PathVariable String id){
        return service.getUser(id).map(AppUtils::user2DTO);
    }

    @GetMapping("/cardnumber/{cardNumber}")
    public Mono<UserDTO> getUserByCardNumber(@PathVariable String cardNumber){
        return service.getUserByCardNumber(cardNumber).map(AppUtils::user2DTO);
    }

    @PostMapping("/cardnumber/credit")
    public Mono<UserDTO> updateUserCredit(@RequestBody Mono<UpdateUserCredit> update){
        System.err.println("Update User credit:");
        return service.updateUserCredit(update).map(AppUtils::user2DTO);
    }

    @PostMapping("")
    public Mono<UserDTO> createNewUser(@RequestBody CreateUserRequest request) {
        return service.saveUser(request).map(AppUtils::user2DTO);
    }

    @GetMapping
    public Flux<UserDTO> getAll(){
        return service.getAllUsers().map(AppUtils::user2DTO);
    }


}
