package ru.gb.april.market.april_market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.april.market.april_market.dto.OrderItemDto;
import ru.gb.april.market.april_market.models.Order;
import ru.gb.april.market.april_market.models.OrderItem;
import ru.gb.april.market.april_market.models.Product;
import ru.gb.april.market.april_market.repositories.ProductRepository;
import ru.gb.april.market.april_market.services.CartService;
import ru.gb.april.market.april_market.services.ProductService;
import ru.gb.april.market.april_market.utils.Cart;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class CartTest {


    @Test
    public void addItemTest() {
        Cart cart = new Cart();
        Product product = new Product ();
        cart.addToCart(product);
        Assertions.assertEquals(1, cart.getItems().size());
    }


    @Test
    public void recalculateTest() {
        Cart cart = new Cart();
        OrderItemDto item1 = new OrderItemDto();
        OrderItemDto item2 = new OrderItemDto();
        OrderItemDto item3 = new OrderItemDto();
        item1.setPrice(BigDecimal.valueOf(8));
        item2.setPrice(BigDecimal.valueOf(20));
        item3.setPrice(BigDecimal.valueOf(30));
        item1.setQuantity(2);
        item2.setQuantity(3);
        item3.setQuantity(4);
        cart.addToCart(item1.getProductId());
        cart.addToCart(item2.getProductId());
        cart.addToCart(item3.getProductId());
        cart.recalculate();
        Assertions.assertEquals(200, cart.getCartPrice());
    }


}
