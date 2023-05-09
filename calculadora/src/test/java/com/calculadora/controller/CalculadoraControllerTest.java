package com.calculadora.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.calculadora.dto.RespuestaMensajeDTO;
import com.calculadora.service.CalculosService;
import com.calculadora.service.impl.CalculosServiceImpl;

import io.corp.calculator.TracerImpl;

@WebMvcTest(CalculadoraController.class)
public class CalculadoraControllerTest {

	@MockBean
	private CalculosService calculosService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSumaOk() throws Exception {
		String a = "2";
		String b = "3";
		int result = 5;

		RespuestaMensajeDTO respuestaDTO = new RespuestaMensajeDTO();
		respuestaDTO.setMensaje(String.valueOf(result));
		respuestaDTO.setCodigoStatus(200);

		when(calculosService.suma(a, b)).thenReturn(respuestaDTO);

		mockMvc.perform(get("/suma").param("a", a).param("b", b)).andExpect(status().isOk())
				.andExpect(jsonPath("$.mensaje").value(String.valueOf(result)))
				.andExpect(jsonPath("$.codigoStatus").value(200));
	}
	
	@Test
	public void testRestaOk() throws Exception {
		String a = "5";
		String b = "2";
		int result = 3;

		RespuestaMensajeDTO respuestaDTO = new RespuestaMensajeDTO();
		respuestaDTO.setMensaje(String.valueOf(result));
		respuestaDTO.setCodigoStatus(200);

		when(calculosService.resta(a, b)).thenReturn(respuestaDTO);

		mockMvc.perform(get("/resta").param("a", a).param("b", b)).andExpect(status().isOk())
				.andExpect(jsonPath("$.mensaje").value(String.valueOf(result)))
				.andExpect(jsonPath("$.codigoStatus").value(200));
	}
	
	@Test
	public void restaError500Test() throws Exception {
	    
	    TracerImpl tracer = mock(TracerImpl.class);
	    CalculosServiceImpl calculosService = new CalculosServiceImpl();
	    calculosService.setTracer(tracer);
	    String a = "2";
	    String b = "abc";
	    ServiceException exception = new ServiceException("Se produjo un error al realizar la resta.");
	    doThrow(exception).when(tracer).trace(anyString());

	    ServiceException result = assertThrows(ServiceException.class, () -> calculosService.resta(a, b));

	    assertEquals(exception.getMessage(), result.getMessage());
	}
	
	@Test
	public void sumaError500Test() throws Exception {

	    TracerImpl tracer = mock(TracerImpl.class);
	    CalculosServiceImpl calculosService = new CalculosServiceImpl();
	    calculosService.setTracer(tracer);
	    String a = "2";
	    String b = "p";
	    ServiceException exception = new ServiceException("Se produjo un error al realizar la suma.");
	    doThrow(exception).when(tracer).trace(anyString());

	    ServiceException result = assertThrows(ServiceException.class, () -> calculosService.suma(a,b));

	    assertEquals(exception.getMessage(), result.getMessage());
	}
	
	@Test
	public void restaError400Test() throws Exception {
	    
	    TracerImpl tracer = mock(TracerImpl.class);
	    CalculosServiceImpl calculosService = new CalculosServiceImpl();
	    calculosService.setTracer(tracer);
	    String a = "2";
	    String b = "";
	    ServiceException exception = new ServiceException("Se produjo un error al realizar la resta.");
	    doThrow(exception).when(tracer).trace(anyString());

	    ServiceException result = assertThrows(ServiceException.class, () -> calculosService.resta(a, b));

	    assertEquals(exception.getMessage(), result.getMessage());
	}
	
	@Test
	public void sumaError400Test() throws Exception {

	    TracerImpl tracer = mock(TracerImpl.class);
	    CalculosServiceImpl calculosService = new CalculosServiceImpl();
	    calculosService.setTracer(tracer);
	    String a = "2";
	    String b = "";
	    ServiceException exception = new ServiceException("Se produjo un error al realizar la suma.");
	    doThrow(exception).when(tracer).trace(anyString());

	    ServiceException result = assertThrows(ServiceException.class, () -> calculosService.suma(a, b));

	    assertEquals(exception.getMessage(), result.getMessage());
	}
	
	
}
