package com.innovateapps.restaurant.domain;

import com.innovateapps.restaurant.domain.response.EnResponseBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodType extends EnResponseBase {
    private Integer foodId;
    private String name;
    private String description;
}
