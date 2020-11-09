package com.innovateapps.restaurant.domain.response;

import com.innovateapps.restaurant.domain.EnviromentType;
import com.innovateapps.restaurant.domain.FoodType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnviromentTypeResponse extends EnResponseBase {

	List<EnviromentType> enviromentTypeList = new ArrayList<>();
}
