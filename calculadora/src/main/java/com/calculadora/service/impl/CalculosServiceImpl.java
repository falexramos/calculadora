package com.calculadora.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.calculadora.dto.RespuestaMensajeDTO;
import com.calculadora.service.CalculosService;

@Service
public class CalculosServiceImpl implements CalculosService {

	@Override
	public RespuestaMensajeDTO suma(String a, String b) {
		RespuestaMensajeDTO respuestaMensajeDTO = new RespuestaMensajeDTO();
		try {
			int intA = Integer.parseInt(a);
			int intB = Integer.parseInt(b);
			int result = intA + intB;
			respuestaMensajeDTO.setMensaje(String.valueOf(result));
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.OK.value());
		} catch (NumberFormatException e) {
			respuestaMensajeDTO.setMensaje("Ocurrió un error al realizar la suma.");
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			respuestaMensajeDTO.setMensaje("Ocurrió un error al realizar la suma");
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return respuestaMensajeDTO;
	}

	@Override
	public RespuestaMensajeDTO resta(String a, String b) {
		RespuestaMensajeDTO respuestaMensajeDTO = new RespuestaMensajeDTO();
		try {
			int intA = Integer.parseInt(a);
			int intB = Integer.parseInt(b);
			int result = intA - intB;
			respuestaMensajeDTO.setMensaje(String.valueOf(result));
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.OK.value());
		} catch (NumberFormatException e) {
			respuestaMensajeDTO.setMensaje("Ocurrió un error al realizar la resta.");
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			respuestaMensajeDTO.setMensaje("Ocurrió un error al realizar la resta.");
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return respuestaMensajeDTO;
	}

}
