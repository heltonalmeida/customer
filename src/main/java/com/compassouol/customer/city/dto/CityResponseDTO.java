package com.compassouol.customer.city.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CityResponseDTO {
	
	private Long id;
	private String name;
	private String state;
	
	@Builder
	public CityResponseDTO(Long id, String name, String state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}

}
