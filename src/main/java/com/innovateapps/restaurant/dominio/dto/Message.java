package com.innovateapps.restaurant.dominio.dto;

import lombok.Data;

@Data
public class Message {
	private String codigo;
	private String mensaje;
	private String tipo;

	public Message(String codigo, String mensaje, String tipo) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.tipo = tipo;
	}
}
