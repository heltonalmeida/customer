package com.compassouol.customer.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compassouol.customer.dto.ExceptionResponseDTO;
import com.compassouol.customer.dto.FieldErrorDTO;
import com.compassouol.customer.dto.FiledExceptionResponseDTO;
import com.compassouol.customer.exception.GenericException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("MethodArgumentNotValidExceptionHandler", ex);
		List<FieldErrorDTO> erros = getMessagesOfField(ex);
		FiledExceptionResponseDTO fieldExceptionResponseDTO = new FiledExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(), erros);
		return new ResponseEntity<>(fieldExceptionResponseDTO, status);
	}

	@ExceptionHandler(GenericException.class)
	protected HttpEntity<ExceptionResponseDTO> handleException(GenericException ex) {
		log.error("GenericExceptionHandler", ex);
		ResponseStatus response = ex.getClass().getAnnotation(ResponseStatus.class);
		HttpStatus status = Objects.nonNull(response) ? response.code() : HttpStatus.INTERNAL_SERVER_ERROR;
		String message = getMessage(ex, response);
		ExceptionResponseDTO exeptionResponseDTO = new ExceptionResponseDTO(status.value(), Arrays.asList(message));
		return new ResponseEntity<>(exeptionResponseDTO, status);
	}

	private List<FieldErrorDTO> getMessagesOfField(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new FieldErrorDTO(error.getField(), error.getDefaultMessage()))
				.collect(Collectors.toList());
	}

	private String getMessage(GenericException ex, ResponseStatus response) {
		return Objects.nonNull(response) && StringUtils.isNotBlank(response.reason()) ? response.reason()
				: ex.getMessage();
	}

}
