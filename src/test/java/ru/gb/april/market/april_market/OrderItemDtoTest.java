package ru.gb.april.market.april_market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.april.market.april_market.dto.OrderItemDto;
import ru.gb.april.market.april_market.models.Product;

import java.math.BigDecimal;


@SpringBootTest
public class OrderItemDtoTest {

    private final Product product;

    public OrderItemDtoTest(){
        this.product = new Product();
        product.setPrice(BigDecimal.valueOf(100));
        product.setTitle("TestProduct");
    }

    @Test
    public void decTest() {
        OrderItemDto orderItemDto = new OrderItemDto(product);
        orderItemDto.decrementQuantity();
        Assertions.assertEquals(0, orderItemDto.getQuantity());
    }

    @Test
    public void incTest() {
        OrderItemDto orderItemDto = new OrderItemDto(product);
        orderItemDto.incrementQuantity();
        Assertions.assertEquals(2, orderItemDto.getQuantity());
    }


}
