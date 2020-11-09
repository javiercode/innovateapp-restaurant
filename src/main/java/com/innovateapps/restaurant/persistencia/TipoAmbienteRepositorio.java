package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.domain.EnviromentType;
import com.innovateapps.restaurant.domain.repository.EnviromentTypeRepository;
import com.innovateapps.restaurant.persistencia.entidad.TipoAmbiente;
import com.innovateapps.restaurant.persistencia.jpa.TipoAmbienteJpaRepositorio;
import com.innovateapps.restaurant.persistencia.mapeo.TipoAmbienteMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoAmbienteRepositorio implements EnviromentTypeRepository {
    @Autowired
    private TipoAmbienteJpaRepositorio tipoAmbienteJpaRepositorio;
    @Autowired
    private TipoAmbienteMapeo mapper;


    @Override
    public Optional<List<EnviromentType>> getLista(){
        Optional<List<TipoAmbiente>> restauranteList = tipoAmbienteJpaRepositorio.recuparLista();
        return restauranteList.map(restaurante -> mapper.toTypeEnviromentList(restaurante));
    }
    @Override
    public List<EnviromentType> getListaPaginada(Integer pagina, Integer cantidad){
        List<TipoAmbiente> restaurantes = tipoAmbienteJpaRepositorio.findAll(PageRequest.of(pagina, cantidad)).getContent();
        return mapper.toTypeEnviromentList( restaurantes);
    }

    @Override
    public Optional<EnviromentType> getOne(Integer id) {
        Optional<TipoAmbiente> restauranteOptional = tipoAmbienteJpaRepositorio.findById(id);
        return restauranteOptional.map(restaurante -> mapper.toTypeEnviroment(restauranteOptional.get()));
    }

    @Override
    public EnviromentType save(EnviromentType enviromentType) {
        TipoAmbiente restaurante = mapper.toTipoAmbiente(enviromentType);
        return mapper.toTypeEnviroment(tipoAmbienteJpaRepositorio.save(restaurante));
    }
    @Override
    public void delete(int id) {
        tipoAmbienteJpaRepositorio.deleteById(id);
    }
}
