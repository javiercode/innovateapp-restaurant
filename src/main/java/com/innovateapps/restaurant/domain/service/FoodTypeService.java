package com.innovateapps.restaurant.domain.service;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.Message;
import com.innovateapps.restaurant.domain.repository.FoodTypeRepository;
import com.innovateapps.restaurant.domain.response.FoodTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.innovateapps.restaurant.domain.response.EnumMessages.*;

@Service
public class FoodTypeService {
    @Autowired
    private FoodTypeRepository foodTypeRepository;

    public FoodTypeResponse getAll(){
        FoodTypeResponse response = new FoodTypeResponse();
        try{
            Optional<List<FoodType>> foodTypeListOpt = foodTypeRepository.getLista();
            List<FoodType> foodTypeList =new ArrayList<>();
            if(foodTypeListOpt.isPresent()){
                foodTypeList = foodTypeListOpt.get();
            }else{
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setFoodTypeList(foodTypeList);
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public FoodTypeResponse getPaginado(Integer pagina, Integer cantidad){
        pagina = pagina==null?0:pagina;
        cantidad = cantidad==null?Integer.MAX_VALUE:cantidad;
        FoodTypeResponse response = new FoodTypeResponse();
        try{
            List<FoodType> foodTypeList  = foodTypeRepository.getListaPaginada(pagina,cantidad);
            if(foodTypeList.isEmpty()){
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setFoodTypeList(foodTypeList);
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public FoodType getOne(Integer id){
        FoodType foodType  = new FoodType();
        Optional<FoodType> foodTypeOptd;
        try{
            Optional<FoodType> foodTypeOpt = foodTypeRepository.getOne(id);
            if(foodTypeOpt.isPresent()){
                foodType = foodTypeOpt.get();
                foodType.setEsCorrecto(true);
            }else{
                foodType.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
        }catch (Exception e){
            foodType.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return foodTypeRepository.save(foodType);
    }

    public FoodType save(FoodType foodType){
        try{
            foodType = foodTypeRepository.save(foodType);
            foodType.setMensaje(new Message(REGISTRO_EXITOSO.getCodigo(),REGISTRO_EXITOSO.getDescripcion(),REGISTRO_EXITOSO.getTipo()));
            foodType.setEsCorrecto(true);
        }catch (Exception e){
            foodType.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return foodTypeRepository.save(foodType);
    }

    public EnResponseBase delete(Integer id){
        EnResponseBase response = new EnResponseBase();
        try{
            foodTypeRepository.delete(id);
            response.setMensaje(new Message(ELIMINACION_EXITOSO.getCodigo(),ELIMINACION_EXITOSO.getDescripcion(),ELIMINACION_EXITOSO.getTipo()));
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return response;
    }
}
