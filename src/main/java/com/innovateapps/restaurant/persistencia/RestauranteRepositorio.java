package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.domain.Restaurant;
import com.innovateapps.restaurant.domain.repository.RestaurantRepository;
import com.innovateapps.restaurant.persistencia.entidad.Restaurante;
import com.innovateapps.restaurant.persistencia.jpa.RestauranteJpaRepositorio;
import com.innovateapps.restaurant.persistencia.mapeo.RestauranteMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.innovateapps.restaurant.domain.IConstantesDomain.ID_USUARIO_DEFECTO;
import static com.innovateapps.restaurant.domain.dto.EnumEstate.NO_VIGENTE;

@Repository
public class RestauranteRepositorio implements RestaurantRepository {
    @Autowired
    private RestauranteJpaRepositorio restauranteJpaRepositorio;
    @Autowired
    private RestauranteMapeo mapper;


    @Override
    public Optional<List<Restaurant>> getLista(){
        Optional<List<Restaurante>> restauranteList = restauranteJpaRepositorio.recuparLista();
        return restauranteList.map(restaurante -> mapper.toRestaurantList( restaurante));
    }
    @Override
    public List<Restaurant> getListaPaginada(Integer pagina, Integer cantidad){
        List<Restaurante> restaurantes = restauranteJpaRepositorio.findAll(PageRequest.of(pagina, cantidad)).getContent();
        return mapper.toRestaurantList( restaurantes);
    }

    @Override
    public Optional<Restaurant> getOne(Integer id) {
        Optional<Restaurante> restauranteOptional = restauranteJpaRepositorio.findById(id);
        return restauranteOptional.map(restaurante -> mapper.toRestaurant(restauranteOptional.get()));
    }


    @Override
    public Restaurant save(Restaurant restaurant) {
        Restaurante restaurante = mapper.toRestaurante(restaurant);
        restaurante.setFechaAlta(LocalDateTime.now());
        restaurante.setIdUsuarioAlta(ID_USUARIO_DEFECTO);
        restaurante.setFechaDesde(LocalDateTime.now());
        restaurante.setIdUsuarioDesde(ID_USUARIO_DEFECTO);
        return mapper.toRestaurant(restauranteJpaRepositorio.save(restaurante));
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Restaurante restaurante = mapper.toRestaurante(restaurant);
        restaurante.setFechaDesde(LocalDateTime.now());
        restaurante.setIdUsuarioDesde(ID_USUARIO_DEFECTO);
        restaurante.setFechaBaja(null);
        restaurante.setIdUsuarioBaja(null);
        return mapper.toRestaurant(restauranteJpaRepositorio.save(restaurante));
    }
    @Override
    public Restaurant delete(int id) {
        Restaurante restaurante = restauranteJpaRepositorio.getOne(id);
        restaurante.setFechaBaja(LocalDateTime.now());
        restaurante.setIdUsuarioBaja(ID_USUARIO_DEFECTO);
        restaurante.setEstado(NO_VIGENTE.getKey());
        restauranteJpaRepositorio.save(restaurante);
        return mapper.toRestaurant(restaurante);
    }
}
