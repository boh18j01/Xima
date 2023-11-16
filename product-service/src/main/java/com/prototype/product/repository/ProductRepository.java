package com.prototype.product.repository;

import com.prototype.product.dto.ProductDto;
import com.prototype.product.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ProductRepository extends ReactiveMongoRepository <Product,String> {

    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);

    Flux<ProductDto> findBySkuCodeIn(List<String> skuCodes);
}