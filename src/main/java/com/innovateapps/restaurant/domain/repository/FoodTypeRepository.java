package com.innovateapps.restaurant.domain.repository;

import com.innovateapps.restaurant.domain.FoodType;

import java.util.List;
import java.util.Optional;

public interface FoodTypeRepository {

    public Optional<List<FoodType>> getLista();
    public List<FoodType> getListaPaginada(Integer pagina, Integer cantidad);
    public Optional<FoodType> getOne(Integer id);
    public FoodType save(FoodType foodType);
    public FoodType update(FoodType foodType);
    public FoodType delete(int id);
}
