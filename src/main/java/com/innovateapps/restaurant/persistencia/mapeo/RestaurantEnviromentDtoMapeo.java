package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.domain.RestaurantEnviroment;
import com.innovateapps.restaurant.domain.dto.RestaurantEnviromentDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RestaurantEnviromentDtoMapeo {
    @Mappings({
            @Mapping(source = "enviromentId", target = "enviromentId"),
            @Mapping(source = "restaurantId", target = "restaurantId"),
            @Mapping(target = "restaurant", ignore = true),
            @Mapping(target = "enviromentType", ignore = true),
            @Mapping(target = "restaurantEnviromentId", ignore = true)
    })
    RestaurantEnviroment toRestaurantEnviroment(RestaurantEnviromentDto enviromentTypeDto);

    @InheritInverseConfiguration
    RestaurantEnviromentDto toRestaurantEnviromentDto(RestaurantEnviroment enviromentType);
}
