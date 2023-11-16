package com.prototype.order.entity;

import com.ecommerce.core.models.Order.OrderStatus;
import com.ecommerce.core.models.products.ProductLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String clientId;
    private String shipTo;
    private LocalDateTime orderedAt;
    private LocalDateTime shippedAt;
    private Double totalPrice;
    private List<ProductLine> products;
    private OrderStatus status;
}
