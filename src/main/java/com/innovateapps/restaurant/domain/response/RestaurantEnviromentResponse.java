package com.innovateapps.restaurant.domain.response;

import com.innovateapps.restaurant.domain.EnviromentType;
import com.innovateapps.restaurant.domain.RestaurantEnviroment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantEnviromentResponse extends EnResponseBase {
	List<RestaurantEnviroment> restaurantEnviromentList = new ArrayList<>();
}
