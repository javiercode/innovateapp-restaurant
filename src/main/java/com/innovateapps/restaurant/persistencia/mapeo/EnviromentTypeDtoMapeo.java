package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.domain.EnviromentType;
import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.dto.EnviromentTypeDto;
import com.innovateapps.restaurant.domain.dto.FoodTypeDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EnviromentTypeDtoMapeo {
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "enviromentId", ignore = true)
    })
    EnviromentType toEnviromentType(EnviromentTypeDto enviromentTypeDto);

    @InheritInverseConfiguration
    EnviromentTypeDto toEnviromentTypeDto(EnviromentType enviromentType);
}
