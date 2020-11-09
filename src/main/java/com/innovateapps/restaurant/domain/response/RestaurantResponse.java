package com.innovateapps.restaurant.domain.response;

import com.innovateapps.restaurant.domain.FoodType;
import com.innovateapps.restaurant.domain.Restaurant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantResponse extends EnResponseBase {
	List<Restaurant> restaurantList = new ArrayList<>();
}