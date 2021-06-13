package ru.gb.april.market.april_market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.april.market.april_market.models.OrderItem;
import ru.gb.april.market.april_market.models.Product;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void incrementQuantity() {
        this.quantity++;
        this.price = this.pricePerProduct.multiply(BigDecimal.valueOf(this.quantity));
    }

    public void decrementQuantity() {
        this.quantity--;
        this.price = this.pricePerProduct.multiply(BigDecimal.valueOf(this.quantity));
    }


}
