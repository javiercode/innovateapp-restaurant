package com.innovateapps.restaurant.persistencia;

import com.innovateapps.restaurant.domain.RestaurantEnviroment;
import com.innovateapps.restaurant.domain.repository.RestaurantEnviromentRepository;
import com.innovateapps.restaurant.persistencia.entidad.RestauranteAmbiente;
import com.innovateapps.restaurant.persistencia.jpa.RestauranteAmbienteJpaRepositorio;
import com.innovateapps.restaurant.persistencia.mapeo.RestauranteAmbienteMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.innovateapps.restaurant.domain.IConstantesDomain.ID_USUARIO_DEFECTO;

@Repository
public class RestauranteAmbienteRepositorio implements RestaurantEnviromentRepository {
    @Autowired
    private RestauranteAmbienteJpaRepositorio restauranteAmbienteJpaRepositorio;
    @Autowired
    private RestauranteAmbienteMapeo mapper;


    @Override
    public Optional<List<RestaurantEnviroment>> getLista(){
        Optional<List<RestauranteAmbiente>> restauranteList = restauranteAmbienteJpaRepositorio.recuparLista();
        return restauranteList.map(restaurante -> mapper.toRestaurantEnviromentList(restaurante));
    }
    @Override
    public List<RestaurantEnviroment> getListaPaginada(Integer pagina, Integer cantidad){
        List<RestauranteAmbiente> restaurantes = restauranteAmbienteJpaRepositorio.findAll(PageRequest.of(pagina, cantidad)).getContent();
        return mapper.toRestaurantEnviromentList( restaurantes);
    }

    @Override
    public Optional<RestaurantEnviroment> getOne(Integer id) {
        Optional<RestauranteAmbiente> restauranteOptional = restauranteAmbienteJpaRepositorio.findById(id);
        return restauranteOptional.map(restaurante -> mapper.toRestaurantEnviroment(restauranteOptional.get()));
    }

    @Override
    public RestaurantEnviroment save(RestaurantEnviroment restaurantEnviroment) {
        RestauranteAmbiente restaurante = mapper.toRestauranteAmbiente(restaurantEnviroment);
        restaurante.setFechaAlta(LocalDateTime.now());
        restaurante.setIdUsuarioAlta(ID_USUARIO_DEFECTO);
        restaurante.setFechaDesde(LocalDateTime.now());
        restaurante.setIdUsuarioDesde(ID_USUARIO_DEFECTO);
        return mapper.toRestaurantEnviroment(restauranteAmbienteJpaRepositorio.save(restaurante));
    }

    @Override
    public RestaurantEnviroment update(RestaurantEnviroment restaurantEnviroment) {
        RestauranteAmbiente restauranteAmbiente = mapper.toRestauranteAmbiente(restaurantEnviroment);
        restauranteAmbiente.setFechaDesde(LocalDateTime.now());
        restauranteAmbiente.setIdUsuarioDesde(ID_USUARIO_DEFECTO);
        restauranteAmbiente.setFechaBaja(null);
        restauranteAmbiente.setIdUsuarioBaja(null);
        return mapper.toRestaurantEnviroment(restauranteAmbienteJpaRepositorio.save(restauranteAmbiente));
    }
    @Override
    public RestaurantEnviroment delete(int id) {
        RestauranteAmbiente restauranteAmbiente = restauranteAmbienteJpaRepositorio.getOne(id);
        restauranteAmbiente.setFechaBaja(LocalDateTime.now());
        restauranteAmbiente.setIdUsuarioBaja(ID_USUARIO_DEFECTO);
        restauranteAmbienteJpaRepositorio.save(restauranteAmbiente);
        return mapper.toRestaurantEnviroment(restauranteAmbiente);
    }
}
