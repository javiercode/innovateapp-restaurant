package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.persistencia.entidad.Restaurante;
import com.innovateapps.restaurant.persistencia.jpa.RestauranteRepositorio;

import java.util.List;

public class TestRepositorio {
    private RestauranteRepositorio restauranteRepositorio;

    public List<Restaurante> getLista(){
        return restauranteRepositorio.findAll();
    }
}
