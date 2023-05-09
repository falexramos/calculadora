package com.calculadora.service.impl;

import org.hibernate.service.spi.ServiceException;
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
	
	public void setTracer(TracerImpl tracer) {
	    this.tracer = tracer;
	}

	
	@Override
	public RespuestaMensajeDTO suma(String a, String b) throws ServiceException{
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
			throw new ServiceException("Los parametros no son números enteros válidos.");
		} catch (Exception e) {
			tracer.trace(e.getMessage());
			throw new ServiceException("Se produjo un error al realizar la suma.");
		}
		return respuestaMensajeDTO;
	}

	@Override
	public RespuestaMensajeDTO resta(String a, String b) throws ServiceException{
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
			throw new ServiceException("Los parametros no son números enteros válidos.");
		} catch (Exception e) {
			tracer.trace(e.getMessage());
			throw new ServiceException("Se produjo un error al realizar la resta.");
		}
		return respuestaMensajeDTO;
	}

}
