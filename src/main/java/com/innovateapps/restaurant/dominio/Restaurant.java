package com.innovateapps.restaurant.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class Restaurant {
    private Integer id;
    private String description;
    private Integer telephone;
    private String state;
    private String logo;
    private String hours;
    private String latitude;
    private String longitud;
    private String address;
    private Integer foodTypeId;
}
