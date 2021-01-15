package com.compassouol.customer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerNameRequestDTO {
	
	@NotBlank
	@Size(max = 100, min = 1)
	private String name;

}
