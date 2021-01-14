package com.compassouol.customer.service;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.compassouol.customer.dto.CustomerRequestDTO;
import com.compassouol.customer.dto.CustomerResponseDTO;
import com.compassouol.customer.enums.Sexo;
import com.compassouol.customer.model.Customer;
import com.compassouol.customer.repository.CustomerRepository;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void save_mustSaveCustomer() {
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("Helton", LocalDate.of(1994, Month.JUNE, 22), Sexo.M, 1l);
		Customer customer = new Customer(1l, "Helton", LocalDate.of(1994, Month.JUNE, 22), Sexo.M, 1l);
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
		
		CustomerResponseDTO result = customerService.save(customerRequestDTO);
		
		Assert.assertNotNull(result);
	}

}
