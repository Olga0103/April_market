package ru.gb.april.market.april_market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.april.market.april_market.models.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getPrice();
    }
}