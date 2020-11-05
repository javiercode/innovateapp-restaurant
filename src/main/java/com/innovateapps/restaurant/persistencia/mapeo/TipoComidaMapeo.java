package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.dominio.FoodType;
import com.innovateapps.restaurant.persistencia.entidad.TipoComida;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoComidaMapeo {
    @Mappings({
            @Mapping(source = "id", target = "foodId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
    })
    FoodType toFoodType(TipoComida tipoComida);
    List<FoodType> toFoodTypeList (List<TipoComida> tipoComidaList);

    @InheritInverseConfiguration
    @Mapping(target = "restauranteList", ignore = true)
    @Mapping(target = "fechaAlta", ignore = true)
    @Mapping(target = "fechaBaja", ignore = true)
    @Mapping(target = "fechaDesde", ignore = true)
    @Mapping(target = "fechaHasta", ignore = true)
    @Mapping(target = "idUsuarioAlta", ignore = true)
    @Mapping(target = "idUsuarioBaja", ignore = true)
    @Mapping(target = "idUsuarioDesde", ignore = true)
    @Mapping(target = "idUsuarioHasta", ignore = true)
    TipoComida toTipoComida(FoodType foodType);
}
