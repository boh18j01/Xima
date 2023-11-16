package com.prototype.shopping.cart.dto;

import com.prototype.shopping.cart.entity.LineItem;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddToCartDto {
    private String clientId;
    private LineItem lineItem;
}
