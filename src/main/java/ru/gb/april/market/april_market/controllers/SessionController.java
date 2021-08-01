package ru.gb.april.market.april_market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.april.market.april_market.utils.SimpleBean;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final SimpleBean simpleBean;

    @GetMapping("/session/get")
    public Integer getSessionData() {
        return simpleBean.getValue();
    }

    @GetMapping("/session/set")
    public void setSessionData(@RequestParam Integer value) {
        simpleBean.setValue(value);
    }
}
