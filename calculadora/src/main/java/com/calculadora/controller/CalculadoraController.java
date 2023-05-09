package com.calculadora.controller;


import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calculadora.dto.RespuestaMensajeDTO;
import com.calculadora.service.CalculosService;

@RestController
public class CalculadoraController {
	
	@Autowired
	private CalculosService calculosService;
	
	@GetMapping("/suma")
	public RespuestaMensajeDTO suma(@RequestParam("a") String a, @RequestParam("b") String b) throws ServiceException{
		return calculosService.suma(a, b);
	}

	@GetMapping("/resta")
	public RespuestaMensajeDTO resta(@RequestParam("a") String a, @RequestParam("b") String b) throws ServiceException {
		return calculosService.resta(a, b);
	}
}
