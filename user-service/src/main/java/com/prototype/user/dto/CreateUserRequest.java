package com.prototype.user.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String address;
}
