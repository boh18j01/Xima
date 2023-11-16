package com.ecommerce.core.models.products;

import com.ecommerce.core.models.products.ProductLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockStatus {
    private String orderId;
    private List<ProductLine> products ;
}
