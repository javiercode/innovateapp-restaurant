package com.innovateapps.restaurant.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticatioRequest {
    private String username;
    private String password;
}
