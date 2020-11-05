package com.innovateapps.restaurant.dominio;

import com.innovateapps.restaurant.dominio.dto.EnResponseBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class FoodType extends EnResponseBase {
    private Integer foodId;
    private String name;
    private String description;
}
