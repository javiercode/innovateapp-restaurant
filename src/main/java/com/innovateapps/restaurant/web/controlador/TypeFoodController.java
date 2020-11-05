package com.innovateapps.restaurant.web.controlador;

import com.innovateapps.restaurant.dominio.FoodType;
import com.innovateapps.restaurant.dominio.dto.EnResponseBase;
import com.innovateapps.restaurant.dominio.response.FoodTypeResponse;
import com.innovateapps.restaurant.dominio.servicio.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foodType")
public class TypeFoodController {
    @Autowired
    private FoodTypeService foodTypeService;

    @GetMapping("/all")
    public ResponseEntity<FoodTypeResponse> getAll() {
        return new ResponseEntity<FoodTypeResponse>(foodTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/paginado")
    public ResponseEntity<FoodTypeResponse> getPaginado(Integer pagina, Integer cantidad) {
        return new ResponseEntity<FoodTypeResponse>(foodTypeService.getPaginado(pagina,cantidad), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<FoodType> registrar(@RequestBody FoodType foodType) {
        return new ResponseEntity<FoodType>(foodTypeService.save(foodType), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EnResponseBase> delete(int id) {
        return new ResponseEntity<EnResponseBase>(foodTypeService.delete(id), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodType> getFoodType(@PathVariable("id") Integer id) {
        FoodType foodType = foodTypeService.getOne(id);
        if(foodType.esCorrecto()){
            return new ResponseEntity<FoodType>(foodTypeService.getOne(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<FoodType>(foodTypeService.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

//    public Optional<FoodType> getProduct(int productId) {
//        return foodTypeService.getProduct(productId);
//    }

//    public Optional<List<FoodType>> getByCategory(int categoryId) {
//        return foodTypeService.getByCategory(categoryId);
//    }

}
