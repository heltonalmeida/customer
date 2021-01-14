package com.compassouol.customer.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.compassouol.customer.enums.Sexo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerResponseDTO {

	private Long id;
	private String name;
	private LocalDate birthDate;
	private Sexo sexo;
	private Long idCity;
	private Long age;

	@Builder
	public CustomerResponseDTO(Long id, String name, LocalDate birthDate, Sexo sexo, Long idCity) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.sexo = sexo;
		this.idCity = idCity;
		this.age = ageCalculate(birthDate);
	}

	private Long ageCalculate(LocalDate birthDate) {
		return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
	}

}
