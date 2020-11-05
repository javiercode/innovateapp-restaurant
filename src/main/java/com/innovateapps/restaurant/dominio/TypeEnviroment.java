package com.innovateapps.restaurant.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class TypeEnviroment {
    private Integer enviromentId;
    private String name;
    private String description;
}
