package ru.gb.april.market.april_market.utils;


import lombok.Data;
import ru.gb.april.market.april_market.dto.OrderItemDto;
import ru.gb.april.market.april_market.models.Product;

import java.math.BigDecimal;
import java.util.*;


@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    public Cart() {
        items = new ArrayList<>();
        sum = BigDecimal.ZERO;
    }

    public boolean addToCart(Long id) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void addToCart(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() { //todo изменить на private после теста
        sum = BigDecimal.ZERO;
        for (OrderItemDto o : items) {
            sum = sum.add(o.getPrice());
        }
    }


    public BigDecimal getCartPrice() {

        return sum;
    }
}
