package com.innovateapps.restaurant.web.controller;

import com.innovateapps.restaurant.domain.EnviromentType;
import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.dto.EnviromentTypeDto;
import com.innovateapps.restaurant.domain.dto.FoodTypeDto;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.EnviromentTypeResponse;
import com.innovateapps.restaurant.domain.response.FoodTypeResponse;
import com.innovateapps.restaurant.domain.service.EnviromentTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enviromentType")
public class EnviromentTypeController {
    @Autowired
    private EnviromentTypeService enviromentTypeService;

    @GetMapping("/all")
    @ApiOperation("Retorna todos los tipos de ambiente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tipos de ambiente no encontrados")
    })
    public ResponseEntity<EnviromentTypeResponse> getAll() {
        EnviromentTypeResponse enviromentTypeResponse = enviromentTypeService.getAll();
        if(enviromentTypeResponse.getEnviromentTypeList().isEmpty()){
            return new ResponseEntity<EnviromentTypeResponse>(enviromentTypeService.getAll(), HttpStatus.OK);
        }else{
            return new ResponseEntity<EnviromentTypeResponse>(enviromentTypeService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginado")
    @ApiOperation("Retorna todos los tipos de ambiente por paginado")
    public ResponseEntity<EnviromentTypeResponse> getPaginado(
            @ApiParam(value = "numero de pagina", required = false, example = "0") Integer pagina,
            @ApiParam(value = "cantidad de registro en la pagina", required = false, example = "5") Integer cantidad) {
        return new ResponseEntity<EnviromentTypeResponse>(enviromentTypeService.getPaginado(pagina,cantidad), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<EnviromentType> registrar(@RequestBody EnviromentTypeDto enviromentTypeDto) {
        return new ResponseEntity<EnviromentType>(enviromentTypeService.save(enviromentTypeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EnResponseBase> eliminar(int id) {
        return new ResponseEntity<EnResponseBase>(enviromentTypeService.delete(id), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnviromentType> obtener(@PathVariable("id") Integer id) {
        EnviromentType enviromentType = enviromentTypeService.getOne(id);
        if(enviromentType.esCorrecto()){
            return new ResponseEntity<EnviromentType>(enviromentTypeService.getOne(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<EnviromentType>(enviromentTypeService.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EnviromentType> actualizar(@RequestBody EnviromentTypeDto enviromentTypeDto,
                                               @PathVariable("id") Integer id) {
        EnviromentType foodType = enviromentTypeService.update(enviromentTypeDto,id);
        if(foodType.esCorrecto()){
            return new ResponseEntity<EnviromentType>(foodType, HttpStatus.OK);
        }else{
            return new ResponseEntity<EnviromentType>(foodType, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
