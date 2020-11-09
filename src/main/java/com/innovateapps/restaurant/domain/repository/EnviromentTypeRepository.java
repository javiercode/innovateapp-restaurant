package com.innovateapps.restaurant.domain.repository;

import com.innovateapps.restaurant.domain.EnviromentType;

import java.util.List;
import java.util.Optional;

public interface EnviromentTypeRepository {

    public Optional<List<EnviromentType>> getLista();

    public List<EnviromentType> getListaPaginada(Integer pagina, Integer cantidad);

    public Optional<EnviromentType> getOne(Integer id);

    public EnviromentType save(EnviromentType enviromentType);
    public void delete(int id);
}
