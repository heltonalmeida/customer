package com.compassouol.customer.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compassouol.customer.converter.CustomerConverter;
import com.compassouol.customer.dto.CustomerRequestDTO;
import com.compassouol.customer.dto.CustomerResponseDTO;
import com.compassouol.customer.exception.CustomerNotFoundException;
import com.compassouol.customer.model.Customer;
import com.compassouol.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerResponseDTO findBy(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
		return CustomerConverter.convert(customer);
	}

	public CustomerResponseDTO save(@Valid CustomerRequestDTO customerRequestDTO) {
		Customer customer = CustomerConverter.convert(customerRequestDTO);
		return CustomerConverter.convert(customerRepository.save(customer));
	}

	public void delete(Long id) {
		findBy(id);
		customerRepository.deleteById(id);
	}

}
