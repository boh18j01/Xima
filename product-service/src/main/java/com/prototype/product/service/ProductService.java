package com.prototype.product.service;

import com.prototype.product.dto.ProductDto;
import com.prototype.product.dto.UpdateProductRequest;
import com.prototype.product.entity.Product;
import com.prototype.product.repository.ProductRepository;
import com.prototype.product.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;



    public Flux<Product> getProducts(){
        return repository.findAll();
    }

    public Mono<Product> getProduct(String id){
        return repository.findById(id);
    }

    public Flux<ProductDto> getProductInRange(double min,double max){
        return repository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<Product> saveProduct(Product product){
        return repository.insert(product);
    }

    public Mono<Product> updateProduct(Product product){
        return repository.save(product);
    }

    public Mono<Product> updateProduct(String id, UpdateProductRequest update){
        return repository.findById(id).flatMap(order -> {
            order.setName(update.getName());
            order.setPrice(update.getPrice());
            order.setTotalInventory(update.getTotalInventory());
            return repository.save(order);
        });
    }


    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }

}
