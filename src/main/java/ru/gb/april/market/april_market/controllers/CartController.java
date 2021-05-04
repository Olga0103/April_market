package ru.gb.april.market.april_market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.utils.Cart;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private Cart cart;

//    @GetMapping("/ping")
//    public void ping(@RequestParam Long id) {
//        log.info("ping: " + id);
//    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        cart.add(id);
        String referrer = httpServletRequest.getHeader("referer");


    }

}
