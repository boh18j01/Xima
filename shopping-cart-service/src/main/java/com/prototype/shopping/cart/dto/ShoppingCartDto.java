package com.prototype.shopping.cart.dto;

import com.prototype.shopping.cart.entity.LineItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShoppingCartDto {
    private String id;
    private String clientId;
    private Double totalPrice;
    private List<LineItem> products;
}
