package com.innovateapps.restaurant.domain;

import com.innovateapps.restaurant.domain.response.EnResponseBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnviromentType extends EnResponseBase {
    private Integer enviromentId;
    private String name;
    private String description;
}
