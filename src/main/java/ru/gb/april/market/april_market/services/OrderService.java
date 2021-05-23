package ru.gb.april.market.april_market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.april.market.april_market.models.Order;
import ru.gb.april.market.april_market.models.OrderItem;
import ru.gb.april.market.april_market.models.User;
import ru.gb.april.market.april_market.repositories.OrderRepository;
import ru.gb.april.market.april_market.utils.Cart;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getSum());
        order.setOrderItems(cart.getItems());
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(order);
        }
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }
}
