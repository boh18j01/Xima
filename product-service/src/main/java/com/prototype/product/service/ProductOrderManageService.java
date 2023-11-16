package com.prototype.product.service;


import com.ecommerce.core.models.products.CheckProductStock;
import com.ecommerce.core.models.products.ProductStockStatus;
import com.prototype.product.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderManageService {

    private static final String SOURCE = "stock";
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOrderManageService.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private KafkaTemplate<String, Object> template;



    public void checkAndReserveProduct(CheckProductStock checkProductStock) {
        checkProductStock.getProducts().forEach(product -> {
            Product availableProduct = this.productService.getProduct(product.getProductId()).block();
            if(availableProduct.getTotalInventory() >= product.getQuantity()){
                product.setAvailable(true);
                availableProduct.setTotalInventory(availableProduct.getTotalInventory() - product.getQuantity());
                System.err.println("Update Product");
                System.err.println(availableProduct.toString());
                this.productService.updateProduct(availableProduct).subscribe();
            }else {
                product.setAvailable(false);
            }
        });

        this.template.send("product-process-response",new ProductStockStatus(checkProductStock.getOrderId(),
                checkProductStock.getProducts()));

    }
}
