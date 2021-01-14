package com.compassouol.customer.converter;

import com.compassouol.customer.dto.CustomerRequestDTO;
import com.compassouol.customer.dto.CustomerResponseDTO;
import com.compassouol.customer.model.Customer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConverter {
	
	public static Customer convert(CustomerRequestDTO customerRequestDTO) {
		return Customer.builder()
				.name(customerRequestDTO.getName())
				.birthDate(customerRequestDTO.getBirthDate())
				.sexo(customerRequestDTO.getSexo())
				.idCity(customerRequestDTO.getIdCity())
				.build();
	}
	
	public static CustomerResponseDTO convert(Customer customer) {
		return CustomerResponseDTO.builder()
				.id(customer.getId())
				.name(customer.getName())
				.birthDate(customer.getBirthDate())
				.sexo(customer.getSexo())
				.idCity(customer.getIdCity())
				.build();
	}

}
