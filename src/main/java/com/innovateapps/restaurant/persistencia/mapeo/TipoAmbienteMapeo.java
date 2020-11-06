package com.innovateapps.restaurant.persistencia.mapeo;

import com.innovateapps.restaurant.domain.TypeEnviroment;
import com.innovateapps.restaurant.persistencia.entidad.TipoAmbiente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoAmbienteMapeo {
    @Mappings({
            @Mapping(source = "id", target = "enviromentId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
    })
    TypeEnviroment toTypeEnviroment(TipoAmbiente tipoAmbiente);
    List<TypeEnviroment> toTypeEnviromentList(List<TipoAmbiente> tipoAmbienteList);

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
    TipoAmbiente toTipoAmbiente(TypeEnviroment typeEnviroment);
}
