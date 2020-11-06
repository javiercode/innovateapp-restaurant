package com.innovateapps.restaurant.domain.repository;

import com.innovateapps.restaurant.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    public Optional<List<Restaurant>> getLista();

    public List<Restaurant> getListaPaginada(Integer pagina, Integer cantidad);

    public Optional<Restaurant> getOne(Integer id);

    public Restaurant save(Restaurant restaurant);
    public void delete(int id);
}
