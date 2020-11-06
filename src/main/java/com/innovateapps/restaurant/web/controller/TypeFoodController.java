package com.innovateapps.restaurant.web.controller;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.dto.FoodTypeDto;
import com.innovateapps.restaurant.domain.response.EnResponseBase;
import com.innovateapps.restaurant.domain.response.FoodTypeResponse;
import com.innovateapps.restaurant.domain.service.FoodTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodType")
public class TypeFoodController {
    @Autowired
    private FoodTypeService foodTypeService;

    @GetMapping("/all")
    @ApiOperation("Retorna todos los tipos de comida")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tipos de comida no encontrados")
    })
    public ResponseEntity<FoodTypeResponse> getAll() {
        FoodTypeResponse foodTypeResponse = foodTypeService.getAll();
        if(foodTypeResponse.getFoodTypeList().isEmpty()){
            return new ResponseEntity<FoodTypeResponse>(foodTypeService.getAll(), HttpStatus.OK);
        }else{
            return new ResponseEntity<FoodTypeResponse>(foodTypeService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginado")
    @ApiOperation("Retorna todos los tipos de comida por paginado")
    public ResponseEntity<FoodTypeResponse> getPaginado(
            @ApiParam(value = "numero de pagina", required = false, example = "0") Integer pagina,
            @ApiParam(value = "cantidad de registro en la pagina", required = false, example = "5") Integer cantidad) {
        return new ResponseEntity<FoodTypeResponse>(foodTypeService.getPaginado(pagina,cantidad), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<FoodType> registrar(@RequestBody FoodTypeDto foodTypeDto) {
        return new ResponseEntity<FoodType>(foodTypeService.save(foodTypeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EnResponseBase> eliminar(int id) {
        return new ResponseEntity<EnResponseBase>(foodTypeService.delete(id), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodType> obtener(@PathVariable("id") Integer id) {
        FoodType foodType = foodTypeService.getOne(id);
        if(foodType.esCorrecto()){
            return new ResponseEntity<FoodType>(foodTypeService.getOne(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<FoodType>(foodTypeService.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<FoodType> actualizar(@RequestBody FoodTypeDto foodTypeDto,
                                               @PathVariable("id") Integer id) {
        FoodType foodType = foodTypeService.update(foodTypeDto,id);
        if(foodType.esCorrecto()){
            return new ResponseEntity<FoodType>(foodType, HttpStatus.OK);
        }else{
            return new ResponseEntity<FoodType>(foodType, HttpStatus.NOT_ACCEPTABLE);
        }
    }

//    public Optional<FoodType> getProduct(int productId) {
//        return foodTypeService.getProduct(productId);
//    }

//    public Optional<List<FoodType>> getByCategory(int categoryId) {
//        return foodTypeService.getByCategory(categoryId);
//    }

}
