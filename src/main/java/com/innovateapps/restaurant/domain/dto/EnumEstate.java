package com.innovateapps.restaurant.domain.dto;

import lombok.Getter;

@Getter
public enum EnumEstate {

    VIGENTE("VIG","Vigente"),
    SUSPENDIDO("SUS","Suspendido"),
    NO_VIGENTE("NVI","No Vigente");

    private String key;
    private String value;

    EnumEstate(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
