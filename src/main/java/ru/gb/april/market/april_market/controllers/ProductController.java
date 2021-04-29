package ru.gb.april.market.april_market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.services.ProductService;

import java.text.ParsePosition;
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
        return productService.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProductById(@PathVariable(value = "id") Long id){
        return productService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Product updateProductById(@RequestBody Product product){
        return productService.updateById(product);
    }
    

}
