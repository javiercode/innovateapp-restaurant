package com.innovateapps.restaurant.web.controller;

import com.innovateapps.restaurant.domain.Restaurant;
import com.innovateapps.restaurant.domain.dto.RestaurantDto;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.RestaurantResponse;
import com.innovateapps.restaurant.domain.response.StateListResponse;
import com.innovateapps.restaurant.domain.service.RestaurantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resturant")
public class ResturantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/estates")
    @ApiOperation("Retorna todos los estados de actividad")
    public ResponseEntity<StateListResponse> getStates() {
        return new ResponseEntity<StateListResponse>(restaurantService.getListaEstados(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation("Retorna todos los resturantes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "resturantes no encontrados")
    })
    public ResponseEntity<RestaurantResponse> getAll() {
        RestaurantResponse restaurantResponse = restaurantService.getAll();
        if(restaurantResponse.getRestaurantList().isEmpty()){
            return new ResponseEntity<RestaurantResponse>(restaurantResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<RestaurantResponse>(restaurantResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginado")
    @ApiOperation("Retorna todos los resturantes por paginado")
    public ResponseEntity<RestaurantResponse> getPaginado(
            @ApiParam(value = "numero de pagina", required = false, example = "0") Integer pagina,
            @ApiParam(value = "cantidad de registro en la pagina", required = false, example = "5") Integer cantidad) {
        return new ResponseEntity<RestaurantResponse>(restaurantService.getPaginado(pagina,cantidad), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Restaurant> registrar(@RequestBody RestaurantDto restaurantDto) {
        return new ResponseEntity<Restaurant>(restaurantService.save(restaurantDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EnResponseBase> eliminar(int id) {
        return new ResponseEntity<EnResponseBase>(restaurantService.delete(id), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> obtener(@PathVariable("id") Integer id) {
        Restaurant restaurant = restaurantService.getOne(id);
        if(restaurant.esCorrecto()){
            return new ResponseEntity<Restaurant>(restaurantService.getOne(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<Restaurant>(restaurantService.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Restaurant> actualizar(@RequestBody RestaurantDto restaurantDto,
                                               @PathVariable("id") Integer id) {
        Restaurant restaurant = restaurantService.update(restaurantDto,id);
        if(restaurant.esCorrecto()){
            return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
        }else{
            return new ResponseEntity<Restaurant>(restaurant, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}