package com.innovateapps.restaurant.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticatioResponse {
    private String jwt;
}
