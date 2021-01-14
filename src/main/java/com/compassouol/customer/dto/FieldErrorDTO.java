package com.compassouol.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FieldErrorDTO {
	
	private String field;
	private String menssage;

}
