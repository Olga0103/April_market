package ru.gb.april.market.april_market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.april.market.april_market.models.Order;
import ru.gb.april.market.april_market.models.User;
import ru.gb.april.market.april_market.repositories.OrderRepository;
import ru.gb.april.market.april_market.utils.Cart;



@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void saveOrder(User user, Cart cart){
        orderRepository.save(new Order(cart, user));
    }
}
