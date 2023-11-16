package com.prototype.shopping.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
    private Product product;
    private Integer quantity;
    private Boolean isAvailable;
}
