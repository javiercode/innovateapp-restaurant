package com.innovateapps.restaurant.domain.service;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.dto.FoodTypeDto;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.Message;
import com.innovateapps.restaurant.domain.repository.FoodTypeRepository;
import com.innovateapps.restaurant.domain.response.FoodTypeResponse;
import com.innovateapps.restaurant.persistencia.mapeo.FoodTypeDtoMapeo;
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
    @Autowired
    private FoodTypeDtoMapeo foodTypeDtoMapeo;

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
        }catch (Exception e){
            response.setEsCorrecto(false);
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
        }catch (Exception e){
            response.setEsCorrecto(false);
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public FoodType getOne(Integer id){
        FoodType foodType  = new FoodType();
        try{
            Optional<FoodType> foodTypeOpt = foodTypeRepository.getOne(id);
            if(foodTypeOpt.isPresent()){
                foodType = foodTypeOpt.get();
            }else{
                foodType.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
        }catch (Exception e){
            foodType.setEsCorrecto(false);
            foodType.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return foodTypeRepository.save(foodType);
    }

    public FoodType save(FoodTypeDto foodTypeDto){
        return administrar(foodTypeDto, new FoodType());
    }

    public FoodType update(FoodTypeDto foodTypeDto, Integer id){
        Optional<FoodType> foodTypeOpt = foodTypeRepository.getOne(id);
        if(foodTypeOpt.isPresent()){
            FoodType foodType = foodTypeDtoMapeo.toFoodType(foodTypeDto);
            foodType.setFoodId(id);
            return administrar(foodTypeDto, foodType);
        }else{
            FoodType foodType = new FoodType();
            foodType.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            return foodType;
        }
    }

    public FoodType administrar(FoodTypeDto foodTypeDto, FoodType foodType){
        foodType.setEsCorrecto(false);
		try {
            if(foodType.getFoodId() == null){
                foodType = foodTypeDtoMapeo.toFoodType (foodTypeDto);
                foodType = foodTypeRepository.save(foodType);
                foodType.setMensaje(new Message(REGISTRO_EXITOSO.getCodigo(),REGISTRO_EXITOSO.getDescripcion(),REGISTRO_EXITOSO.getTipo()));
            }else{
                foodType = foodTypeRepository.save(foodType);
                foodType.setMensaje(new Message(REGISTRO_ACTUALIZADO.getCodigo(),REGISTRO_ACTUALIZADO.getDescripcion(),REGISTRO_ACTUALIZADO.getTipo()));
            }
		}catch (Exception p){
            foodType.setEsCorrecto(false);
            foodType.setMensaje(new Message(PARAMETROS_INCORRECTOS.getCodigo(),PARAMETROS_INCORRECTOS.getDescripcion(),PARAMETROS_INCORRECTOS.getTipo()));
		}
        return foodType;
    }

    public EnResponseBase delete(Integer id){
        EnResponseBase response = new EnResponseBase();
        try{
            foodTypeRepository.delete(id);
            response.setMensaje(new Message(ELIMINACION_EXITOSO.getCodigo(),ELIMINACION_EXITOSO.getDescripcion(),ELIMINACION_EXITOSO.getTipo()));
        }catch (Exception e){
            response.setEsCorrecto(false);
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return response;
    }
}
