package com.innovateapps.restaurant.domain.service;

import com.innovateapps.restaurant.domain.EnviromentType;
import com.innovateapps.restaurant.domain.dto.EnviromentTypeDto;
import com.innovateapps.restaurant.domain.repository.EnviromentTypeRepository;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.EnviromentTypeResponse;
import com.innovateapps.restaurant.domain.response.Message;
import com.innovateapps.restaurant.persistencia.mapeo.EnviromentTypeDtoMapeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.innovateapps.restaurant.domain.response.EnumMessages.*;

@Service
public class EnviromentTypeService {
    @Autowired
    private EnviromentTypeRepository enviromentTypeRepository;
    @Autowired
    private EnviromentTypeDtoMapeo typeEnviromentDtoMapeo;

    public EnviromentTypeResponse getAll(){
        EnviromentTypeResponse response = new EnviromentTypeResponse();
        try{
            Optional<List<EnviromentType>> enviromentTypeListOpt = enviromentTypeRepository.getLista();
            List<EnviromentType> enviromentTypeList =new ArrayList<>();
            if(enviromentTypeListOpt.isPresent()){
                enviromentTypeList = enviromentTypeListOpt.get();
            }else{
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setEnviromentTypeList(enviromentTypeList);
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public EnviromentTypeResponse getPaginado(Integer pagina, Integer cantidad){
        pagina = pagina==null?0:pagina;
        cantidad = cantidad==null?Integer.MAX_VALUE:cantidad;
        EnviromentTypeResponse response = new EnviromentTypeResponse();
        try{
            List<EnviromentType> enviromentTypeList  = enviromentTypeRepository.getListaPaginada(pagina,cantidad);
            if(enviromentTypeList.isEmpty()){
                response.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
            response.setEnviromentTypeList(enviromentTypeList);
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
        }
        return response;
    }

    public EnviromentType getOne(Integer id){
        EnviromentType enviromentType  = new EnviromentType();
        try{
            Optional<EnviromentType> enviromentTypeOptional = enviromentTypeRepository.getOne(id);
            if(enviromentTypeOptional.isPresent()){
                enviromentType = enviromentTypeOptional.get();
                enviromentType.setEsCorrecto(true);
            }else{
                enviromentType.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            }
        }catch (Exception e){
            enviromentType.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return enviromentTypeRepository.save(enviromentType);
    }

    public EnviromentType save(EnviromentTypeDto enviromentTypeDto){
        return administrar(enviromentTypeDto, new EnviromentType());
    }

    public EnviromentType update(EnviromentTypeDto enviromentTypeDto, Integer id){
        Optional<EnviromentType> enviromentTypeOptional = enviromentTypeRepository.getOne(id);
        if(enviromentTypeOptional.isPresent()){
            EnviromentType enviromentType = typeEnviromentDtoMapeo.toEnviromentType(enviromentTypeDto);
            enviromentType.setEnviromentId(id);
            return administrar(enviromentTypeDto, enviromentType);
        }else{
            EnviromentType enviromentType = new EnviromentType();
            enviromentType.setMensaje(new Message(REGISTRO_NO_ENCONTRADO.getCodigo(),REGISTRO_NO_ENCONTRADO.getDescripcion(),REGISTRO_NO_ENCONTRADO.getTipo()));
            return enviromentType;
        }
    }

    public EnviromentType administrar(EnviromentTypeDto enviromentTypeDto, EnviromentType enviromentType){
        enviromentType.setEsCorrecto(false);
		try {
            if(enviromentType.equals(new EnviromentType())){
                enviromentType = typeEnviromentDtoMapeo.toEnviromentType(enviromentTypeDto);
                enviromentType = enviromentTypeRepository.save(enviromentType);
                enviromentType.setMensaje(new Message(REGISTRO_EXITOSO.getCodigo(),REGISTRO_EXITOSO.getDescripcion(),REGISTRO_EXITOSO.getTipo()));
            }else{
                enviromentType = enviromentTypeRepository.save(enviromentType);
                enviromentType.setMensaje(new Message(REGISTRO_ACTUALIZADO.getCodigo(),REGISTRO_ACTUALIZADO.getDescripcion(),REGISTRO_ACTUALIZADO.getTipo()));
            }
            enviromentType.setEsCorrecto(true);

		}catch (Exception p){
            enviromentType.setMensaje(new Message(PARAMETROS_INCORRECTOS.getCodigo(),PARAMETROS_INCORRECTOS.getDescripcion(),PARAMETROS_INCORRECTOS.getTipo()));
		}
        return enviromentType;
    }

    public EnResponseBase delete(Integer id){
        EnResponseBase response = new EnResponseBase();
        try{
            enviromentTypeRepository.delete(id);
            response.setMensaje(new Message(ELIMINACION_EXITOSO.getCodigo(),ELIMINACION_EXITOSO.getDescripcion(),ELIMINACION_EXITOSO.getTipo()));
            response.setEsCorrecto(true);
        }catch (Exception e){
            response.setMensaje(new Message(ERROR_BASE_DATOS.getCodigo(),ERROR_BASE_DATOS.getDescripcion(),ERROR_BASE_DATOS.getTipo()));
            e.printStackTrace();
        }
        return response;
    }
}
