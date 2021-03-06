package com.innovateapps.restaurant.domain.response;


import com.innovateapps.restaurant.domain.dto.EnumTypeMessage;
import lombok.Getter;

@Getter
public enum EnumMessages {

    ERROR_BASE_DATOS("Hubo un error en la base de datos","INA-GG-0001", EnumTypeMessage.ADVERTENCIA.getCodigo()),
    REGISTRO_EXITOSO("Se registro exitosamente","INA-GG-0002",EnumTypeMessage.INFORMACION.getCodigo()),
    REGISTRO_ACTUALIZADO("El registro fue actualizado","DAS-EP-0003",EnumTypeMessage.INFORMACION.getCodigo()),
    ELIMINACION_EXITOSO("Se elimino exitosamente","INA-GG-0004",EnumTypeMessage.INFORMACION.getCodigo()),
    ERROR_RECUPERAR_INFO("No se pudo encontrar la informacion","INA-GG-0005",EnumTypeMessage.ERROR.getCodigo()),
    PARAMETROS_INCORRECTOS("Los datos ingresados son incorrectos","INA-GG-0006",EnumTypeMessage.ERROR.getCodigo()),
    REGISTRO_NO_ENCONTRADO("El registro no se encontro","INA-GG-0007",EnumTypeMessage.ADVERTENCIA.getCodigo()),
    USUARIO_NO_VALIDO("El usuario no esta habilitado en la BD","INA-GG-0008",EnumTypeMessage.ADVERTENCIA.getCodigo()),
    REGISTRO_VACIO("Ningun registro econtrado","INA-GG-0009",EnumTypeMessage.INFORMACION.getCodigo()
//    PARAMETROS_INCORRECTOS("Hubo un error en la base de datos","INA-GG-0001", EnumTypeMessage.ADVERTENCIA.getCodigo()
    );

    private String descripcion;
    private String codigo;
    private String tipo;

    private EnumMessages(String descripcion, String codigo, String tipo) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.tipo = tipo;
    }
}