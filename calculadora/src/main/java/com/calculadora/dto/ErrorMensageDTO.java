package com.calculadora.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorMensageDTO implements Serializable {
	/**
	 * Clase para mostrar los mensajes de error
	 */

	private static final long serialVersionUID = 1L;

	public ErrorMensageDTO(String message) {
		super();
		this.message = message;
	}

	private String message;
}
