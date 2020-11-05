package com.innovateapps.restaurant.dominio.response;

import java.util.ArrayList;
import java.util.List;

import com.innovateapps.restaurant.dominio.FoodType;
import com.innovateapps.restaurant.dominio.dto.EnResponseBase;
import lombok.Data;
import lombok.Getter;

@Data
public class FoodTypeResponse extends EnResponseBase {

	List<FoodType> foodTypeList = new ArrayList<>();
}
