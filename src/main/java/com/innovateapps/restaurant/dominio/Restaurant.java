package com.innovateapps.restaurant.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class Restaurant {
    private Integer resturantId;
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
    private FoodType foodType;
}
