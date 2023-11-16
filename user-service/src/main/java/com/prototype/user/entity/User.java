package com.prototype.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Builder
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    @Indexed(unique = true)
    private String cardnumber;
    private double availableCredit;
    private double creditLimit;
}
