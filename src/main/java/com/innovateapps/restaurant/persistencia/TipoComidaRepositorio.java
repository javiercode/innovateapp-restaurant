package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.repository.FoodTypeRepository;
import com.innovateapps.restaurant.persistencia.entidad.TipoComida;
import com.innovateapps.restaurant.persistencia.jpa.TipoComidaJpaRepositorio;
import com.innovateapps.restaurant.persistencia.mapeo.TipoComidaMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.innovateapps.restaurant.domain.dto.IConstantesDomain.ID_USUARIO_DEFECTO;

@Repository
public class TipoComidaRepositorio implements FoodTypeRepository {
    @Autowired
    private TipoComidaJpaRepositorio tipoComidaJpaRepositorio;
    @Autowired
    private TipoComidaMapeo mapper;


    @Override
    public Optional<List<FoodType>> getLista(){
        Optional<List<TipoComida>> restauranteList = tipoComidaJpaRepositorio.recuparLista();
        return restauranteList.map(restaurante -> mapper.toFoodTypeList(restaurante));
    }
    @Override
    public List<FoodType> getListaPaginada(Integer pagina, Integer cantidad){
        List<TipoComida> restaurantes = tipoComidaJpaRepositorio.recuparPaginado(PageRequest.of(pagina, cantidad)).getContent();
        return mapper.toFoodTypeList( restaurantes);
    }

    @Override
    public Optional<FoodType> getOne(Integer id) {
        Optional<TipoComida> restauranteOptional = tipoComidaJpaRepositorio.findById(id);
        return restauranteOptional.map(restaurante -> mapper.toFoodType(restauranteOptional.get()));
    }

    @Override
    public FoodType save(FoodType foodType) {
        TipoComida restaurante = mapper.toTipoComida(foodType);
        restaurante.setFechaAlta(LocalDateTime.now());
        restaurante.setIdUsuarioAlta(ID_USUARIO_DEFECTO);
        restaurante.setFechaDesde(LocalDateTime.now());
        restaurante.setIdUsuarioDesde(ID_USUARIO_DEFECTO);
        return mapper.toFoodType(tipoComidaJpaRepositorio.save(restaurante));
    }

    @Override
    public FoodType update(FoodType foodType) {
        TipoComida restaurante = mapper.toTipoComida(foodType);
        restaurante.setFechaDesde(LocalDateTime.now());
        restaurante.setIdUsuarioDesde(ID_USUARIO_DEFECTO);
        restaurante.setFechaBaja(null);
        restaurante.setIdUsuarioBaja(null);
        return mapper.toFoodType(tipoComidaJpaRepositorio.save(restaurante));
    }
    @Override
    public FoodType delete(int id) {
        TipoComida tipoComida = tipoComidaJpaRepositorio.getOne(id);
        tipoComida.setFechaBaja(LocalDateTime.now());
        tipoComida.setIdUsuarioBaja(ID_USUARIO_DEFECTO);
        tipoComidaJpaRepositorio.save(tipoComida);
        return mapper.toFoodType(tipoComida);
    }
}
