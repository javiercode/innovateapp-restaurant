package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.domain.Restaurant;
import com.innovateapps.restaurant.domain.repository.RestaurantRepository;
import com.innovateapps.restaurant.persistencia.entidad.Restaurante;
import com.innovateapps.restaurant.persistencia.jpa.RestauranteJpaRepositorio;
import com.innovateapps.restaurant.persistencia.mapeo.RestauranteMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        return mapper.toRestaurant(restauranteJpaRepositorio.save(restaurante));
    }
    @Override
    public void delete(int id) {
        restauranteJpaRepositorio.deleteById(id);
    }
}
