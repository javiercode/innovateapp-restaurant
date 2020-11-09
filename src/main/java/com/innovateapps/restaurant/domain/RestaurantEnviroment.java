package com.innovateapps.restaurant.domain;

import com.innovateapps.restaurant.domain.response.EnResponseBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEnviroment extends EnResponseBase {
    private Integer restaurantEnviromentId;
    private Integer restaurantId;
    private Integer enviromentId;
    private Restaurant restaurant;
    private EnviromentType enviromentType;
}