package com.innovateapps.restaurant.web.controller;

import com.innovateapps.restaurant.domain.Restaurant;
import com.innovateapps.restaurant.domain.RestaurantEnviroment;
import com.innovateapps.restaurant.domain.dto.RestaurantDto;
import com.innovateapps.restaurant.domain.dto.RestaurantEnviromentDto;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.RestaurantEnviromentResponse;
import com.innovateapps.restaurant.domain.service.RestaurantEnviromentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resturantEnviroment")
public class ResturantEnviromentController {
    @Autowired
    private RestaurantEnviromentService restaurantEnviromentService;

    @GetMapping("/all")
    @ApiOperation("Retorna todos los resturantes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "resturantes no encontrados")
    })
    public ResponseEntity<RestaurantEnviromentResponse> getAll() {
        RestaurantEnviromentResponse restaurantResponse = restaurantEnviromentService.getAll();
        if(restaurantResponse.getRestaurantEnviromentList().isEmpty()){
            return new ResponseEntity<RestaurantEnviromentResponse>(restaurantEnviromentService.getAll(), HttpStatus.OK);
        }else{
            return new ResponseEntity<RestaurantEnviromentResponse>(restaurantEnviromentService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginado")
    @ApiOperation("Retorna todos los resturantes por paginado")
    public ResponseEntity<RestaurantEnviromentResponse> getPaginado(
            @ApiParam(value = "numero de pagina", required = false, example = "0") Integer pagina,
            @ApiParam(value = "cantidad de registro en la pagina", required = false, example = "5") Integer cantidad) {
        return new ResponseEntity<RestaurantEnviromentResponse>(restaurantEnviromentService.getPaginado(pagina,cantidad), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<RestaurantEnviroment> registrar(@RequestBody RestaurantEnviromentDto restaurantDto) {
        return new ResponseEntity<RestaurantEnviroment>(restaurantEnviromentService.save(restaurantDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EnResponseBase> eliminar(int id) {
        return new ResponseEntity<EnResponseBase>(restaurantEnviromentService.delete(id), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantEnviroment> obtener(@PathVariable("id") Integer id) {
        RestaurantEnviroment restaurantEnviroment = restaurantEnviromentService.getOne(id);
        if(restaurantEnviroment.esCorrecto()){
            return new ResponseEntity<RestaurantEnviroment>(restaurantEnviromentService.getOne(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<RestaurantEnviroment>(restaurantEnviromentService.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<RestaurantEnviroment> actualizar(@RequestBody RestaurantEnviromentDto restaurantDto,
                                               @PathVariable("id") Integer id) {
        RestaurantEnviroment restaurantEnviroment = restaurantEnviromentService.update(restaurantDto,id);
        if(restaurantEnviroment.esCorrecto()){
            return new ResponseEntity<RestaurantEnviroment>(restaurantEnviroment, HttpStatus.OK);
        }else{
            return new ResponseEntity<RestaurantEnviroment>(restaurantEnviroment, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}