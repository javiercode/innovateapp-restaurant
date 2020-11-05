package com.innovateapps.restaurant.dominio.repositorio;

import com.innovateapps.restaurant.dominio.FoodType;
import com.innovateapps.restaurant.dominio.RestaurantEnviroment;

import java.util.List;
import java.util.Optional;

public interface FoodTypeRepository {

    public Optional<List<FoodType>> getLista();

    public List<FoodType> getListaPaginada(Integer pagina, Integer cantidad);

    public Optional<FoodType> getOne(Integer id);

    public FoodType save(FoodType foodType);
    public void delete(int id);
}
