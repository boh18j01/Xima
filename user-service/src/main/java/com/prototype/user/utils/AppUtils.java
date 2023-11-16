package com.prototype.user.utils;

import com.prototype.user.dto.CreateUserRequest;
import com.prototype.user.dto.UserDTO;
import com.prototype.user.entity.User;

import java.util.Random;

public class AppUtils {


    static public UserDTO user2DTO(User user){
        return UserDTO.builder()
                .cardNumber(user.getCardnumber())
                .availableCredit(user.getAvailableCredit())
                .creditLimit(user.getCreditLimit()).build();
    }

    static public User createUser(CreateUserRequest user){
        Random rnd = new Random();
        return User.builder()
                .name(user.getName())
                .address(user.getAddress())
                .email(user.getAddress())
                .creditLimit(2000)
                .availableCredit(2000)
                .cardnumber(String.valueOf(rnd.nextLong(Long.MAX_VALUE)))
                .build();
    }


}
