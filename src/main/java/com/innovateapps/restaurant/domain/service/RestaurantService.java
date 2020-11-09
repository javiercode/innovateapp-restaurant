package com.innovateapps.restaurant.domain.service;

import com.innovateapps.restaurant.domain.Restaurant;
import com.innovateapps.restaurant.domain.dto.EnumEstate;
import com.innovateapps.restaurant.domain.dto.RestaurantDto;
import com.innovateapps.restaurant.domain.dto.State;
import com.innovateapps.restaurant.domain.repository.RestaurantRepository;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.Message;
import com.innovateapps.restaurant.domain.response.RestaurantResponse;
import com.innovateapps.restaurant.domain.response.StateListResponse;
import com.innovateapps.restaurant.persistencia.mapeo.RestaurantDtoMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.innovateapps.restaurant.domain.response.EnumMessages.*;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantDtoMapeo restaurantDtoMapeo;

    public RestaurantResponse getAll(){
        RestaurantResponse response = new RestaurantResponse();
        try{
            Optional<List<Restaurant>> optionalRestaurantList = restaurantRepository.getLista();
            List<Restaurant> restaurantList =new ArrayList<>();
            if(optionalRestaurantList.isPresent()){
                restaurantList = optionalRestaurantList.get();
            }else{
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setRestaurantList(restaurantList);
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public RestaurantResponse getPaginado(Integer pagina, Integer cantidad){
        pagina = pagina==null?0:pagina;
        cantidad = cantidad==null?Integer.MAX_VALUE:cantidad;
        RestaurantResponse response = new RestaurantResponse();
        try{
            List<Restaurant> restaurantList  = restaurantRepository.getListaPaginada(pagina,cantidad);
            if(restaurantList.isEmpty()){
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setRestaurantList(restaurantList);
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public Restaurant getOne(Integer id){
        Restaurant restaurant  = new Restaurant();
        try{
            Optional<Restaurant> resturantOpt = restaurantRepository.getOne(id);
            if(resturantOpt.isPresent()){
                restaurant = resturantOpt.get();
                restaurant.setEsCorrecto(true);
            }else{
                restaurant.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
        }catch (Exception e){
            restaurant.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return restaurantRepository.save(restaurant);
    }

    public Restaurant save(RestaurantDto restaurantDto){
        return administrar(restaurantDto, new Restaurant());
    }

    public Restaurant update(RestaurantDto resturantDto, Integer id){
        Optional<Restaurant> resturantOpt = restaurantRepository.getOne(id);
        if(resturantOpt.isPresent()){
            Restaurant restaurant = restaurantDtoMapeo.toRestaurant(resturantDto);
            restaurant.setResturantId(id);
            return administrar(resturantDto, restaurant);
        }else{
            Restaurant restaurant = new Restaurant();
            restaurant.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            return restaurant;
        }
    }

    public Restaurant administrar(RestaurantDto resurantDto, Restaurant restaurant){
        restaurant.setEsCorrecto(false);
		try {
            if(restaurant.getResturantId()==null){
                restaurant = restaurantDtoMapeo.toRestaurant(resurantDto);
                restaurant = restaurantRepository.save(restaurant);
                restaurant.setMensaje(new Message(REGISTRO_EXITOSO.getCodigo(),REGISTRO_EXITOSO.getDescripcion(),REGISTRO_EXITOSO.getTipo()));
            }else{
                restaurant = restaurantRepository.save(restaurant);
                restaurant.setMensaje(new Message(REGISTRO_ACTUALIZADO.getCodigo(),REGISTRO_ACTUALIZADO.getDescripcion(),REGISTRO_ACTUALIZADO.getTipo()));
            }
            restaurant.setEsCorrecto(true);

		}catch (Exception p){
            restaurant.setMensaje(new Message(PARAMETROS_INCORRECTOS.getCodigo(),PARAMETROS_INCORRECTOS.getDescripcion(),PARAMETROS_INCORRECTOS.getTipo()));
		}
        return restaurant;
    }

    public EnResponseBase delete(Integer id){
        EnResponseBase response = new EnResponseBase();
        try{
            restaurantRepository.delete(id);
            response.setMensaje(new Message(ELIMINACION_EXITOSO.getCodigo(),ELIMINACION_EXITOSO.getDescripcion(),ELIMINACION_EXITOSO.getTipo()));
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return response;
    }
    public StateListResponse getListaEstados(){
        StateListResponse listResponse = new StateListResponse();
        List<State> enumNames = Stream.of(EnumEstate.values())
                .map(enumEstate -> {
                    return new State(enumEstate.getKey(),enumEstate.getValue());
                })
                .collect(Collectors.toList());
        listResponse.setStateList(enumNames);
        return listResponse;
    }
}
