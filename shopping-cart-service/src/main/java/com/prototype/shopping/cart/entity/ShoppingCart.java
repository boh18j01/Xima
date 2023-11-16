package com.prototype.shopping.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carts")
public class ShoppingCart {
    @Id
    private String id;
    private String clientId;
    private Double totalPrice;
    private List<LineItem> products;
}
