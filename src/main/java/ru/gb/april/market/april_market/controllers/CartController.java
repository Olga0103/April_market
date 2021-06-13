package ru.gb.april.market.april_market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.services.CartService;
import ru.gb.april.market.april_market.utils.Cart;



@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping("/add")
    public void addToCart(@RequestParam(name = "prodId") Long id, @RequestParam String cartName) {
        cartService.addToCart(cartName, id);
    }

    @GetMapping("/clear")
    public void clearCart(@RequestParam String cartName) {
        cartService.clearCart(cartName);
    }

    @GetMapping
    public Cart getCart(@RequestParam String cartName) {
        return cartService.getCurrentCart(cartName);
    }

}
