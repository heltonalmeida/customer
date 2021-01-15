package com.compassouol.customer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FiledExceptionResponseDTO {

	private int status;
	private List<FieldErrorDTO> erros;

}
