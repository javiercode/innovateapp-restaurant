package com.innovateapps.restaurant.domain.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnResponseBase {

	private boolean esCorrecto = true;
	private List<Message> mensajes = new ArrayList<Message>();

	public boolean esCorrecto() {
		return esCorrecto;
	}

	public void setEsCorrecto(boolean esCorrecto) {
		this.esCorrecto = esCorrecto;
	}

	public List<Message> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Message> mensajes) {
		this.mensajes = mensajes;
	}

	public void setMensaje(Message mensaje){
		List<Message> mensajeListPartial = this.getMensajes();
		mensajeListPartial.add(mensaje);
		this.setMensajes(mensajeListPartial);
	}
}
