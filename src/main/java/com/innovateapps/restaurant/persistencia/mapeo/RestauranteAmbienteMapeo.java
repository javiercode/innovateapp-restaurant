package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.domain.RestaurantEnviroment;
import com.innovateapps.restaurant.persistencia.entidad.RestauranteAmbiente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TipoAmbienteMapeo.class,RestauranteMapeo.class})
public interface RestauranteAmbienteMapeo {
    @Mappings({
            @Mapping(source = "id", target = "restaurantEnviromentId"),
            @Mapping(source = "idRestaurante", target = "restaurantId"),
            @Mapping(source = "idTipoAmbiente", target = "enviromentId"),
            @Mapping(source = "restaurante", target = "restaurant"),
            @Mapping(source = "tipoAmbiente", target = "typeEnviroment"),
    })
    RestaurantEnviroment toRestaurantEnviroment(RestauranteAmbiente restauranteAmbiente);
    List<RestaurantEnviroment> toRestaurantEnviromentList(List<RestauranteAmbiente> restauranteAmbientes);

    @InheritInverseConfiguration
    @Mapping(target = "fechaAlta", ignore = true)
    @Mapping(target = "fechaBaja", ignore = true)
    @Mapping(target = "fechaDesde", ignore = true)
    @Mapping(target = "fechaHasta", ignore = true)
    @Mapping(target = "idUsuarioAlta", ignore = true)
    @Mapping(target = "idUsuarioBaja", ignore = true)
    @Mapping(target = "idUsuarioDesde", ignore = true)
    @Mapping(target = "idUsuarioHasta", ignore = true)
    RestauranteAmbiente toRestauranteAmbiente(RestaurantEnviroment restaurantEnviroment);
}