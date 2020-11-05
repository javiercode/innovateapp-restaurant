package com.innovateapps.restaurant.dominio.repositorio;

import com.innovateapps.restaurant.dominio.Restaurant;
import com.innovateapps.restaurant.dominio.RestaurantEnviroment;
import com.innovateapps.restaurant.persistencia.entidad.Restaurante;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface RestaurantEnviromentRepository {

    public Optional<List<RestaurantEnviroment>> getLista();

    public List<RestaurantEnviroment> getListaPaginada(Integer pagina, Integer cantidad);

    public Optional<RestaurantEnviroment> getOne(Integer id);

    public RestaurantEnviroment save(RestaurantEnviroment restaurantEnviroment);
    public void delete(int id);
}
