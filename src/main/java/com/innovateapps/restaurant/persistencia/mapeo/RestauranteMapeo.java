package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.domain.Restaurant;
import com.innovateapps.restaurant.persistencia.entidad.Restaurante;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TipoComidaMapeo.class})
//@Mapper(componentModel = "spring")
public interface RestauranteMapeo {
    @Mappings({
            @Mapping(source = "id", target = "resturantId"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "telefono", target = "telephone"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "logo", target = "logo"),
            @Mapping(source = "horario", target = "hours"),
            @Mapping(source = "latitud", target = "latitude"),
            @Mapping(source = "longitud", target = "longitude"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "idTipoComida", target = "foodTypeId"),
            @Mapping(source = "tipoComida", target = "foodType"),
    })
    Restaurant toRestaurant(Restaurante restaurante);
    List<Restaurant> toRestaurantList(List<Restaurante> restauranteList);

    @InheritInverseConfiguration
    @Mapping(target = "restauranteAmbienteList", ignore = true)
    @Mapping(target = "fechaAlta", ignore = true)
    @Mapping(target = "fechaBaja", ignore = true)
    @Mapping(target = "fechaDesde", ignore = true)
    @Mapping(target = "fechaHasta", ignore = true)
    @Mapping(target = "idUsuarioAlta", ignore = true)
    @Mapping(target = "idUsuarioBaja", ignore = true)
    @Mapping(target = "idUsuarioDesde", ignore = true)
    @Mapping(target = "idUsuarioHasta", ignore = true)
    Restaurante toRestaurante(Restaurant restaurant);
}
