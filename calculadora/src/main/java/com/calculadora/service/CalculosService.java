package com.calculadora.service;

import com.calculadora.dto.RespuestaMensajeDTO;

public interface CalculosService {
	RespuestaMensajeDTO suma(String a, String b);

	RespuestaMensajeDTO resta(String a, String b);
}
