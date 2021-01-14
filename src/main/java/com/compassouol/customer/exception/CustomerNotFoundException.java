package com.compassouol.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cliente não encontrado")
public class CustomerNotFoundException extends GenericException {

	private static final long serialVersionUID = 1L;

}
