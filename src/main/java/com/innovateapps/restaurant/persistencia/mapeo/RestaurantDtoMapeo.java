package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.Restaurant;
import com.innovateapps.restaurant.domain.dto.FoodTypeDto;
import com.innovateapps.restaurant.domain.dto.RestaurantDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RestaurantDtoMapeo {
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "telephone", target = "telephone"),
            @Mapping(source = "state", target = "state"),
            @Mapping(source = "logo", target = "logo"),
            @Mapping(source = "hours", target = "hours"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "foodTypeId", target = "foodTypeId"),
            @Mapping(target = "resturantId", ignore = true),
            @Mapping(target = "foodType", ignore = true)
    })
    Restaurant toRestaurant(RestaurantDto restaurantDto);

    @InheritInverseConfiguration
    RestaurantDto toRestaurantDto(Restaurant restaurant);
}
