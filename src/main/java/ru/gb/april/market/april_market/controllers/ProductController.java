package ru.gb.april.market.april_market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.dto.ProductDto;
import ru.gb.april.market.april_market.error_handlings.InvalidDataException;
import ru.gb.april.market.april_market.error_handlings.MarketError;
import ru.gb.april.market.april_market.error_handlings.ResourceNotFoundException;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") int page) {
        Page<Product> productsPage = productService.findPage(page - 1, 10);
        Page<ProductDto> dtoPage = new PageImpl<>(productsPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), productsPage.getPageable(), productsPage.getTotalElements());
        return dtoPage;
    }

    @GetMapping("/{id}")
    public ProductDto getOneProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id));
        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return productService.createNewProduct(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
