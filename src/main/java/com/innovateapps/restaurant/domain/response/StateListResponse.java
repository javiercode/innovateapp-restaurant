package com.innovateapps.restaurant.domain.response;

import com.innovateapps.restaurant.domain.dto.State;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StateListResponse extends EnResponseBase{
	private List<State> stateList = new ArrayList<>();
}