package com.innovateapps.restaurant.domain.response;

import java.util.ArrayList;
import java.util.List;

import com.innovateapps.restaurant.domain.FoodType;
import lombok.Data;

@Data
public class FoodTypeResponse extends EnResponseBase {

	List<FoodType> foodTypeList = new ArrayList<>();
}
