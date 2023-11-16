package com.prototype.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateProductRequest {
    private String name ;
    private int availableStock;
    private double price;
    private String skuCode;
}
