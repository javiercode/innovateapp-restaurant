package com.innovateapps.restaurant.domain.repository;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.RestaurantEnviroment;

import java.util.List;
import java.util.Optional;

public interface RestaurantEnviromentRepository {

    public Optional<List<RestaurantEnviroment>> getLista();
    public List<RestaurantEnviroment> getListaPaginada(Integer pagina, Integer cantidad);
    public Optional<RestaurantEnviroment> getOne(Integer id);
    public RestaurantEnviroment save(RestaurantEnviroment restaurantEnviroment);
    public RestaurantEnviroment update(RestaurantEnviroment restaurantEnviroment);
    public RestaurantEnviroment delete(int id);
}
