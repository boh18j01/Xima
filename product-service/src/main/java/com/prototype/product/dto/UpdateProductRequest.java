package com.prototype.product.dto;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String name ;
    private int totalInventory;
    private double price;
}
