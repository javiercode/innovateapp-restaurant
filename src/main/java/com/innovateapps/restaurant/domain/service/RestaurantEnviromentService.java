package com.innovateapps.restaurant.domain.service;

import com.innovateapps.restaurant.domain.RestaurantEnviroment;
import com.innovateapps.restaurant.domain.dto.RestaurantEnviromentDto;
import com.innovateapps.restaurant.domain.repository.RestaurantEnviromentRepository;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.RestaurantEnviromentResponse;
import com.innovateapps.restaurant.domain.response.Message;
import com.innovateapps.restaurant.persistencia.mapeo.RestaurantEnviromentDtoMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.innovateapps.restaurant.domain.response.EnumMessages.*;

@Service
public class RestaurantEnviromentService {
    @Autowired
    private RestaurantEnviromentRepository restaurantEnviromentRepository;
    @Autowired
    private RestaurantEnviromentDtoMapeo mapperDto;

    public RestaurantEnviromentResponse getAll(){
        RestaurantEnviromentResponse response = new RestaurantEnviromentResponse();
        try{
            Optional<List<RestaurantEnviroment>> restaurantEnviroments = restaurantEnviromentRepository.getLista();
            List<RestaurantEnviroment> restaurantEnviromentList = new ArrayList<>();
            if(restaurantEnviroments.isPresent()){
                restaurantEnviromentList = restaurantEnviroments.get();
            }else{
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setRestaurantEnviromentList(restaurantEnviromentList);
        }catch (Exception e){
            response.setEsCorrecto(false);
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public RestaurantEnviromentResponse getPaginado(Integer pagina, Integer cantidad){
        pagina = pagina==null?0:pagina;
        cantidad = cantidad==null?Integer.MAX_VALUE:cantidad;
        RestaurantEnviromentResponse response = new RestaurantEnviromentResponse();
        try{
            List<RestaurantEnviroment> restaurantEnviromentList  = restaurantEnviromentRepository.getListaPaginada(pagina,cantidad);
            if(restaurantEnviromentList.isEmpty()){
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setRestaurantEnviromentList(restaurantEnviromentList);
        }catch (Exception e){
            response.setEsCorrecto(false);
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public RestaurantEnviroment getOne(Integer id){
        RestaurantEnviroment restaurantEnviroment  = new RestaurantEnviroment();
        try{
            Optional<RestaurantEnviroment> restaurantEnviromentOptional = restaurantEnviromentRepository.getOne(id);
            if(restaurantEnviromentOptional.isPresent()){
                restaurantEnviroment = restaurantEnviromentOptional.get();
            }else{
                restaurantEnviroment.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
        }catch (Exception e){
            restaurantEnviroment.setEsCorrecto(false);
            restaurantEnviroment.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return restaurantEnviromentRepository.save(restaurantEnviroment);
    }

    public RestaurantEnviroment save(RestaurantEnviromentDto restaurantEnviromentDto){
        return administrar(restaurantEnviromentDto, new RestaurantEnviroment());
    }

    public RestaurantEnviroment update(RestaurantEnviromentDto restaurantEnviromentDto, Integer id){
        Optional<RestaurantEnviroment> enviromentRepositoryOne = restaurantEnviromentRepository.getOne(id);
        if(enviromentRepositoryOne.isPresent()){
            RestaurantEnviroment restaurantEnviroment = mapperDto.toRestaurantEnviroment(restaurantEnviromentDto);
            restaurantEnviroment.setEnviromentId(id);
            return administrar(restaurantEnviromentDto, restaurantEnviroment);
        }else{
            RestaurantEnviroment restaurantEnviroment = new RestaurantEnviroment();
            restaurantEnviroment.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            return restaurantEnviroment;
        }
    }

    public RestaurantEnviroment administrar(RestaurantEnviromentDto restaurantEnviromentDto, RestaurantEnviroment restaurantEnviroment){
        restaurantEnviroment.setEsCorrecto(false);
		try {
            if(restaurantEnviroment.getEnviromentId() == null){
                restaurantEnviroment = mapperDto.toRestaurantEnviroment(restaurantEnviromentDto);
                restaurantEnviroment = restaurantEnviromentRepository.save(restaurantEnviroment);
                restaurantEnviroment.setMensaje(new Message(REGISTRO_EXITOSO.getCodigo(),REGISTRO_EXITOSO.getDescripcion(),REGISTRO_EXITOSO.getTipo()));
            }else{
                restaurantEnviroment = restaurantEnviromentRepository.save(restaurantEnviroment);
                restaurantEnviroment.setMensaje(new Message(REGISTRO_ACTUALIZADO.getCodigo(),REGISTRO_ACTUALIZADO.getDescripcion(),REGISTRO_ACTUALIZADO.getTipo()));
            }
		}catch (Exception p){
            restaurantEnviroment.setEsCorrecto(false);
            restaurantEnviroment.setMensaje(new Message(PARAMETROS_INCORRECTOS.getCodigo(),PARAMETROS_INCORRECTOS.getDescripcion(),PARAMETROS_INCORRECTOS.getTipo()));
		}
        return restaurantEnviroment;
    }

    public EnResponseBase delete(Integer id){
        EnResponseBase response = new EnResponseBase();
        try{
            restaurantEnviromentRepository.delete(id);
            response.setMensaje(new Message(ELIMINACION_EXITOSO.getCodigo(),ELIMINACION_EXITOSO.getDescripcion(),ELIMINACION_EXITOSO.getTipo()));
        }catch (Exception e){
            response.setEsCorrecto(false);
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return response;
    }
}
