package com.calculadora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.calculadora.dto.RespuestaMensajeDTO;
import com.calculadora.service.CalculosService;
import io.corp.calculator.TracerImpl;

@Service
public class CalculosServiceImpl implements CalculosService {
	
	@Autowired
	private TracerImpl tracer;
	
	@Override
	public RespuestaMensajeDTO suma(String a, String b) {
		RespuestaMensajeDTO respuestaMensajeDTO = new RespuestaMensajeDTO();
		try {
			int intA = Integer.parseInt(a);
			int intB = Integer.parseInt(b);
			int result = intA + intB;
			tracer.trace(result);
			respuestaMensajeDTO.setMensaje(String.valueOf(result));
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.OK.value());
		} catch (NumberFormatException e) {
			tracer.trace(e.getMessage());
			respuestaMensajeDTO.setMensaje("Ocurrió un error al realizar la suma.");
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			tracer.trace(e.getMessage());
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
			tracer.trace(result);
			respuestaMensajeDTO.setMensaje(String.valueOf(result));
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.OK.value());
		} catch (NumberFormatException e) {
			tracer.trace(e.getMessage());
			respuestaMensajeDTO.setMensaje("Ocurrió un error al realizar la resta.");
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			tracer.trace(e.getMessage());
			respuestaMensajeDTO.setMensaje("Ocurrió un error al realizar la resta.");
			respuestaMensajeDTO.setCodigoStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return respuestaMensajeDTO;
	}

}
