package com.innovateapps.restaurant.dominio.repositorio;

import com.innovateapps.restaurant.dominio.RestaurantEnviroment;
import com.innovateapps.restaurant.dominio.TypeEnviroment;

import java.util.List;
import java.util.Optional;

public interface TypeEnviromentRepository {

    public Optional<List<TypeEnviroment>> getLista();

    public List<TypeEnviroment> getListaPaginada(Integer pagina, Integer cantidad);

    public Optional<TypeEnviroment> getOne(Integer id);

    public TypeEnviroment save(TypeEnviroment typeEnviroment);
    public void delete(int id);
}
