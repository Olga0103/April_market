package ru.gb.april.market.april_market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.april.market.april_market.dto.OrderItemDto;
import ru.gb.april.market.april_market.models.Order;
import ru.gb.april.market.april_market.models.OrderItem;
import ru.gb.april.market.april_market.models.User;
import ru.gb.april.market.april_market.repositories.OrderRepository;
import ru.gb.april.market.april_market.utils.Cart;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user) {
        Order order = new Order();
        order.setUser(user);
        Cart cart = cartService.getCurrentCart("cart"); // todo ERROR
        order.setPrice(cart.getSum());
        // todo распутать этот кусок
        order.setItems(new ArrayList<>());
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            order.getItems().add(orderItem);
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            orderItem.setPricePerProduct(o.getPricePerProduct());
            orderItem.setPrice(o.getPrice());
            orderItem.setProduct(productService.findById(o.getProductId()).get());
        }
        order = orderRepository.save(order);
        cart.clear();
        cartService.save("cart", cart); // todo ERROR
        return order;
    }
}
