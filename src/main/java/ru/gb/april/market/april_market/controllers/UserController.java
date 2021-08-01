package ru.gb.april.market.april_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.gb.april.market.april_market.dto.UserDto;
import ru.gb.april.market.april_market.error_handlings.ResourceNotFoundException;
import ru.gb.april.market.april_market.error_handlings.UserIsAlreadyRegisteredException;
import ru.gb.april.market.april_market.models.User;
import ru.gb.april.market.april_market.services.UserService;


import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    public UserDto getCurrentUsername(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(()
                -> new ResourceNotFoundException("User doesn't exist"));
        return new UserDto(currentUser.getUsername(), currentUser.getPassword(), currentUser.getEmail());
    }

    @PostMapping("/register")
    public void register(@RequestParam String username, @RequestParam String password, @RequestParam String email ) throws UserIsAlreadyRegisteredException {

        if (userService.findByUsername(username).isPresent()){
            throw new UserIsAlreadyRegisteredException("Пользователь " + username +  " уже зарегистрирован");
        }
        userService.save(username,passwordEncoder.encode(password), email);
    }
}
