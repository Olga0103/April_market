package ru.gb.april.market.april_market.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class Cart {

    private ProductService productService;

    @Autowired
    public void setProductsService(ProductService productService) {
        this.productService = productService;
    }

    private List<Long> cartProducts;



    public Cart(){
        cartProducts = new ArrayList<Long>();
    }

    public void add(Long id) {

        Optional<Product> product = productService.findById(id);
        cartProducts.add(id);
    }



}
