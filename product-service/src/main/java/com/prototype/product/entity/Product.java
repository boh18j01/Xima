package com.prototype.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection  = "products")
@Builder
public class Product {

    @Id
    private String id ;
    private String name ;
    private int reservedItems;
    private int totalInventory;
    private double price;
    private String skuCode;


}