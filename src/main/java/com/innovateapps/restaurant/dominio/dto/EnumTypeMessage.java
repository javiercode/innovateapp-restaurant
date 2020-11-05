package com.innovateapps.restaurant.dominio.dto;

import lombok.Getter;

@Getter
public enum EnumTypeMessage {

    ERROR("ERROR","Error"),
    INFORMACION("INFORMACION","Información"),
    ADVERTENCIA("ADVERTENCIA","Advertencia");

    private String codigo;
    private String detalle;

    EnumTypeMessage(String codigo, String detalle) {
        this.codigo = codigo;
        this.detalle = detalle;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetalle() {
        return detalle;
    }
}
