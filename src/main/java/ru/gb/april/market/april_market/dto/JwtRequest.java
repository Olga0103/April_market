package ru.gb.april.market.april_market.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}

