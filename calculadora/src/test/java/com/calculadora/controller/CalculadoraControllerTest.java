package com.calculadora.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.calculadora.dto.RespuestaMensajeDTO;
import com.calculadora.service.CalculosService;

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
	
}
