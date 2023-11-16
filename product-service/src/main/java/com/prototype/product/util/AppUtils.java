package com.prototype.product.util;

import com.prototype.product.dto.CreateProductRequest;
import com.prototype.product.dto.ProductDto;
import com.prototype.product.entity.Product;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class AppUtils {
    static public ProductDto productToDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .totalInventory(product.getTotalInventory())
                .build();
    }

    static public Product createProduct(CreateProductRequest request){
            return  Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name(request.getName())
                    .price(request.getPrice())
                    .reservedItems(0)
                    .totalInventory(request.getAvailableStock())
                    .build();
    }
}
