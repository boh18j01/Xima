package com.ecommerce.core.models.products;

import lombok.Data;

@Data
public class ProductLine {
    private String productId;
    private int quantity;
    private boolean isAvailable;
}
