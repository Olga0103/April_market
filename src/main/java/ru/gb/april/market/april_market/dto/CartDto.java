package ru.gb.april.market.april_market.dto;

import lombok.Data;
import ru.gb.april.market.april_market.utils.Cart;


import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private Long id;
    private List<CartItemDto> cartItems;
    private int cartPrice;

    public CartDto(Cart cart) {
        this.id = cart.getId();
        this.cartPrice = cart.getCartPrice();
        this.cartItems = (List<CartItemDto>) cart.getCartItems();
    }
}
