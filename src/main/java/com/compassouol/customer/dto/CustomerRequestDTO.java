package com.compassouol.customer.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.compassouol.customer.enums.Sexo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerRequestDTO {

	@NotBlank
	@Size(max = 100, min = 1)
	private String name;

	@NotNull
	@PastOrPresent
	private LocalDate birthDate;

	@NotNull
	private Sexo sexo;

	@NotNull
	private Long idCity;

	@Builder
	public CustomerRequestDTO(String name, LocalDate birthDate, Sexo sexo, Long idCity) {
		this.name = name;
		this.birthDate = birthDate;
		this.sexo = sexo;
		this.idCity = idCity;
	}

}
