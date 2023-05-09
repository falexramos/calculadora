package com.calculadora.handler;

import java.text.MessageFormat;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.calculadora.dto.ErrorMensageDTO;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIlegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMensageDTO(ex.getMessage()), new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {
		request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMensageDTO(ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOthersExceptions(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex,
				new ErrorMensageDTO(MessageFormat.format(
						"Ocurri\u00f3 un error de tipo {0}. Contacte al administrador del sistema.",
						ex.getClass().getName())),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorMensageDTO errorMessageDTO = new ErrorMensageDTO("La petición no es correcta");

		return handleExceptionInternal(ex, errorMessageDTO, headers, status, request);
	}

}
