package ru.gb.april.market.april_market.dto;

import lombok.Data;
import ru.gb.april.market.april_market.utils.Cart;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    public CartDto(Cart cart) {
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }
}
