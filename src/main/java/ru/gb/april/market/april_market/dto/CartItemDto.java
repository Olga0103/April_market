package ru.gb.april.market.april_market.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private ProductDto product;
    private int cost;



}