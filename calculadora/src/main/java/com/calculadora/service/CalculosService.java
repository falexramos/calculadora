package com.calculadora.service;

import org.hibernate.service.spi.ServiceException;

import com.calculadora.dto.RespuestaMensajeDTO;

public interface CalculosService {
	RespuestaMensajeDTO suma(String a, String b) throws ServiceException;

	RespuestaMensajeDTO resta(String a, String b) throws ServiceException;
}
