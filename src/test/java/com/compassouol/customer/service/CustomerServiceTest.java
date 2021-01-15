package com.compassouol.customer.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.compassouol.customer.dto.CustomerNameRequestDTO;
import com.compassouol.customer.dto.CustomerRequestDTO;
import com.compassouol.customer.dto.CustomerResponseDTO;
import com.compassouol.customer.enums.Sexo;
import com.compassouol.customer.exception.CustomerNotFoundException;
import com.compassouol.customer.model.Customer;
import com.compassouol.customer.repository.CustomerRepository;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void findBy_mustReturnCustomerList() {
	    String name = "Helton";
	    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
	    Mockito.when(customerRepository.findBy(Mockito.anyString(), Mockito.any())).thenReturn(new PageImpl<>(new ArrayList<>()));
	    
	    Page<CustomerResponseDTO> result = customerService.findBy(name, pageRequest);
	    
	    Assert.assertNotNull(result);
	}
	
	@Test
	public void findBy_mustReturnCustomer() {
		Long id = 1l;
		Customer customer = new Customer(1l, "Helton", LocalDate.of(1994, Month.JUNE, 22), Sexo.M, 1l);
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
		
		CustomerResponseDTO result = customerService.findBy(id);
		
		Assert.assertNotNull(result);
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void findBy_mustThrowExceptionWhenCustomerIsNotFound() {
		Long id = 1l;
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		
		customerService.findBy(id);
	}
	
	@Test
	public void save_mustSaveCustomer() {
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("Helton", LocalDate.of(1994, Month.JUNE, 22), Sexo.M, 1l);
		Customer customer = new Customer(1l, "Helton", LocalDate.of(1994, Month.JUNE, 22), Sexo.M, 1l);
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
		
		CustomerResponseDTO result = customerService.save(customerRequestDTO);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void delete_mustDeleteCustomer() {
		Long id = 1l;
		Customer customer = new Customer(1l, "Helton", LocalDate.of(1994, Month.JUNE, 22), Sexo.M, 1l);
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
		
		customerService.delete(id);
		
		Mockito.verify(customerRepository).delete(customer);
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void delete_mustThrowExceptionWhenCustomerIsNotFound() {
		Long id = 1l;
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		
		customerService.delete(id);
		
		Mockito.verify(customerRepository).deleteById(id);
	}
	
	@Test
	public void updateName_mustUpdateName() {
		Long id = 1l;
		CustomerNameRequestDTO customerNameRequestDTO = new CustomerNameRequestDTO("Helton2");
		Customer customer = new Customer(1l, "Helton", LocalDate.of(1994, Month.JUNE, 22), Sexo.M, 1l);
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
		
		CustomerResponseDTO result = customerService.updateName(id, customerNameRequestDTO);
		
		Assert.assertEquals(customerNameRequestDTO.getName(), result.getName());
		
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void updateName_mustThrowExceptionWhenCustomerIsNotFound() {
		Long id = 1l;
		CustomerNameRequestDTO customerNameRequestDTO = new CustomerNameRequestDTO("Helton2");
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		
		customerService.updateName(id, customerNameRequestDTO);
	}

}
