package com.compassouol.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compassouol.customer.city.feign.CityClientFeign;
import com.compassouol.customer.converter.CustomerConverter;
import com.compassouol.customer.dto.CustomerNameRequestDTO;
import com.compassouol.customer.dto.CustomerRequestDTO;
import com.compassouol.customer.dto.CustomerResponseDTO;
import com.compassouol.customer.exception.CustomerNotFoundException;
import com.compassouol.customer.model.Customer;
import com.compassouol.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CityClientFeign cityClient;
	
	public Page<CustomerResponseDTO> findBy(String name, Pageable page) {
		return customerRepository.findBy(name, page);
	}
	
	public CustomerResponseDTO findBy(Long id) {
		Customer customer = findById(id);
		return CustomerConverter.convert(customer);
	}

	public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
		validateRegisteredCity(customerRequestDTO);
		Customer customer = CustomerConverter.convert(customerRequestDTO);
		return CustomerConverter.convert(customerRepository.save(customer));
	}

	public void delete(Long id) {
		Customer customer = findById(id);
		customerRepository.delete(customer);
	}

	public CustomerResponseDTO updateName(Long id, CustomerNameRequestDTO customerNameRequestDTO) {
		Customer customer = findById(id);
		customer.setName(customerNameRequestDTO.getName());
		return CustomerConverter.convert(customerRepository.save(customer));
	}
	
	private void validateRegisteredCity(CustomerRequestDTO customerRequestDTO) {
		cityClient.findBy(customerRequestDTO.getIdCity());
	}
	
	private Customer findById(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
	}

}
