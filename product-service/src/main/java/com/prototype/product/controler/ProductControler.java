package com.prototype.product.controler;



import com.ecommerce.core.models.products.ProductLine;
import com.prototype.product.dto.CreateProductRequest;
import com.prototype.product.dto.ProductDto;
import com.prototype.product.dto.UpdateProductRequest;
import com.prototype.product.service.ProductService;
import com.prototype.product.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin()
@RequestMapping("/products")
public class ProductControler {

    @Autowired
    public ProductService service;

    @GetMapping("/")
    public Flux<ProductDto> products(){
        return service.getProducts().map(AppUtils::productToDto);
    }

    @GetMapping("/index")
    public String index() {
        return "Heeeeey";
    }
    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return service.getProduct(id).map(AppUtils::productToDto);
    }

    @GetMapping("/product-range/")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max){
        return service.getProductInRange(min,max);
    }


    @PostMapping
    public Mono<ProductDto> createProduct(@RequestBody CreateProductRequest request){
        return service.saveProduct(AppUtils.createProduct(request)).map(AppUtils::productToDto);
    }

    @PostMapping("/available")
    public Mono<Boolean> getIsAllProductsAvailable(@RequestBody Flux<ProductLine> products) {
        System.out.println("checking.");
        return null;
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct( @PathVariable String id, @RequestBody UpdateProductRequest updateRequest){
        return service.updateProduct(id,updateRequest).map(AppUtils::productToDto);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }
}
