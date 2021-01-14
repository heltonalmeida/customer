package com.compassouol.customer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponseDTO {

	private int status;
	private List<String> menssage;

}
