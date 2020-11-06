package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.domain.TypeEnviroment;
import com.innovateapps.restaurant.domain.repository.TypeEnviromentRepository;
import com.innovateapps.restaurant.persistencia.entidad.TipoAmbiente;
import com.innovateapps.restaurant.persistencia.jpa.TipoAmbienteJpaRepositorio;
import com.innovateapps.restaurant.persistencia.mapeo.TipoAmbienteMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoAmbienteRepositorio implements TypeEnviromentRepository {
    @Autowired
    private TipoAmbienteJpaRepositorio tipoAmbienteJpaRepositorio;
    @Autowired
    private TipoAmbienteMapeo mapper;


    @Override
    public Optional<List<TypeEnviroment>> getLista(){
        Optional<List<TipoAmbiente>> restauranteList = tipoAmbienteJpaRepositorio.recuparLista();
        return restauranteList.map(restaurante -> mapper.toTypeEnviromentList(restaurante));
    }
    @Override
    public List<TypeEnviroment> getListaPaginada(Integer pagina, Integer cantidad){
        List<TipoAmbiente> restaurantes = tipoAmbienteJpaRepositorio.findAll(PageRequest.of(pagina, cantidad)).getContent();
        return mapper.toTypeEnviromentList( restaurantes);
    }

    @Override
    public Optional<TypeEnviroment> getOne(Integer id) {
        Optional<TipoAmbiente> restauranteOptional = tipoAmbienteJpaRepositorio.findById(id);
        return restauranteOptional.map(restaurante -> mapper.toTypeEnviroment(restauranteOptional.get()));
    }

    @Override
    public TypeEnviroment save(TypeEnviroment typeEnviroment) {
        TipoAmbiente restaurante = mapper.toTipoAmbiente(typeEnviroment);
        return mapper.toTypeEnviroment(tipoAmbienteJpaRepositorio.save(restaurante));
    }
    @Override
    public void delete(int id) {
        tipoAmbienteJpaRepositorio.deleteById(id);
    }
}
