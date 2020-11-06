package com.innovateapps.restaurant.domain.dto;

import com.innovateapps.restaurant.domain.response.EnResponseBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodTypeDto{
    private String name;
    private String description;
}