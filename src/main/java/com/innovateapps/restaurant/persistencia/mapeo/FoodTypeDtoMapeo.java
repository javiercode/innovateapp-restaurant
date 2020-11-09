package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.dto.FoodTypeDto;
import com.innovateapps.restaurant.persistencia.entidad.TipoComida;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FoodTypeDtoMapeo {
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "foodId", ignore = true)
    })

    FoodType toFoodType(FoodTypeDto foodTypeDto);

    @InheritInverseConfiguration
    FoodTypeDto toFoodTypeDto(FoodType foodType);
}
