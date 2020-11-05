package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.dominio.FoodType;
import com.innovateapps.restaurant.dominio.RestaurantEnviroment;
import com.innovateapps.restaurant.dominio.repositorio.FoodTypeRepository;
import com.innovateapps.restaurant.dominio.repositorio.RestaurantEnviromentRepository;
import com.innovateapps.restaurant.persistencia.entidad.RestauranteAmbiente;
import com.innovateapps.restaurant.persistencia.entidad.TipoComida;
import com.innovateapps.restaurant.persistencia.jpa.TipoComidaJpaRepositorio;
import com.innovateapps.restaurant.persistencia.mapeo.RestauranteAmbienteMapeo;
import com.innovateapps.restaurant.persistencia.mapeo.TipoComidaMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.innovateapps.restaurant.dominio.dto.IConstantesDomain.ID_USUARIO_DEFECTO;

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
        List<TipoComida> restaurantes = tipoComidaJpaRepositorio.findAll(PageRequest.of(pagina, cantidad)).getContent();
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
    public void delete(int id) {
        tipoComidaJpaRepositorio.deleteById(id);
    }
}
