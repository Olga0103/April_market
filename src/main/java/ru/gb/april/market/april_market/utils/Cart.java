package ru.gb.april.market.april_market.utils;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.april.market.april_market.error_handlings.ResourceNotFoundException;
import ru.gb.april.market.april_market.models.OrderItem;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;


@Component
@Data
@RequiredArgsConstructor
public class Cart {

    private final ProductService productService;
    private List<OrderItem> items;
    private BigDecimal sum;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }

        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        items.add(new OrderItem(product));
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItem oi : items) {
            sum = sum.add(oi.getPrice());
        }
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }



}
