package ru.gb.april.market.april_market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.error_handlings.MarketError;
import ru.gb.april.market.april_market.error_handlings.ResourceNotFoundException;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProductById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product doesn't exists: " + id));
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product){
        List<String> errors = new ArrayList<>();
        if (product.getTitle().length() < 3) {
            errors.add("Too short title");
        }
        if (product.getPrice() < 1) {
            errors.add("Invalid product price");
        }
        if (errors.size() > 0) {
            return new ResponseEntity<>
                    (new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
        Product out = productService.save(product);
        return new ResponseEntity<>(out, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable(value = "id") Long id){
        productService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateProductById(@RequestBody Product product){
        productService.updateById(product);
    }


}
