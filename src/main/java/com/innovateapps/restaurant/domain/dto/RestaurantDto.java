package com.innovateapps.restaurant.domain.dto;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.persistencia.entidad.TipoComida;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RestaurantDto {
    private String name;
    private String description;
    private Integer telephone;
    private String state;
    private String logo;
    private String hours;
    private String latitude;
    private String longitude;
    private String address;
    private Integer foodTypeId;
//    private FoodType foodType;
}