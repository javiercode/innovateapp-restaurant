package com.innovateapps.restaurant.dominio.repositorio;

import com.innovateapps.restaurant.dominio.Restaurant;
import com.innovateapps.restaurant.persistencia.entidad.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    public Optional<List<Restaurant>> getLista();

    public List<Restaurant> getListaPaginada(Integer pagina, Integer cantidad);

    public Optional<Restaurant> getOne(Integer id);

    public Restaurant save(Restaurant restaurant);
    public void delete(int id);
}
