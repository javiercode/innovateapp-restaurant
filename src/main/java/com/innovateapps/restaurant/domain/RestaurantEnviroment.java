package com.innovateapps.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEnviroment {
    private Integer restaurantEnviromentId;
    private Integer restaurantId;
    private Integer enviromentId;
    private Restaurant restaurant;
    private TypeEnviroment typeEnviroment;
}