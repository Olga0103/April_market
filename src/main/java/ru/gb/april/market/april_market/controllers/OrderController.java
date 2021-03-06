package ru.gb.april.market.april_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.dto.OrderDto;
import ru.gb.april.market.april_market.models.User;
import ru.gb.april.market.april_market.services.OrderService;
import ru.gb.april.market.april_market.services.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createNewOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        orderService.createOrderForCurrentUser(user);
    }

    @GetMapping
    @Transactional
    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }

}