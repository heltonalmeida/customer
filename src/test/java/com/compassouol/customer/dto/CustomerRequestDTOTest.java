package com.compassouol.customer.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import com.compassouol.customer.enums.Sexo;

public class CustomerRequestDTOTest {

	private final static String TEXTO_COM_MAIS_DE_100_CARACTERES = "teste_1234567890_teste_1234567890_teste_1234567890_teste_1234567890_teste_1234567890_teste_1234567890";
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void CustomerRequestDTO_shouldNotHasConstraintViolation() {
		CustomerRequestDTO param = new CustomerRequestDTO();
		param.setName("name");
		param.setSexo(Sexo.M);
		param.setBirthDate(LocalDate.now());
		param.setIdCity(1L);

		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(param);

		Assert.assertEquals(0, violations.size());
	}

	@Test
	public void CustomerRequestDTO_shouldHasConstraintViolationNameBlank() {
		CustomerRequestDTO param = new CustomerRequestDTO();
		param.setSexo(Sexo.M);
		param.setBirthDate(LocalDate.now());
		param.setIdCity(1L);

		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CustomerRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("name", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CustomerRequestDTO_shouldHasConstraintViolationNameSize() {
		CustomerRequestDTO param = new CustomerRequestDTO();
		param.setName(TEXTO_COM_MAIS_DE_100_CARACTERES);
		param.setSexo(Sexo.M);
		param.setBirthDate(LocalDate.now());
		param.setIdCity(1L);

		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CustomerRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("name", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CustomerRequestDTO_shouldHasConstraintViolationSexoNull() {
		CustomerRequestDTO param = new CustomerRequestDTO();
		param.setName("name");
		param.setBirthDate(LocalDate.now());
		param.setIdCity(1L);

		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CustomerRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("sexo", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CustomerRequestDTO_shouldHasConstraintViolationbirthDateNull() {
		CustomerRequestDTO param = new CustomerRequestDTO();
		param.setName("nome");
		param.setSexo(Sexo.M);
		param.setIdCity(1L);

		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CustomerRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("birthDate", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CustomerRequestDTO_shouldHasConstraintViolationbirthDateFuture() {
		CustomerRequestDTO param = new CustomerRequestDTO();
		param.setName("nome");
		param.setSexo(Sexo.M);
		param.setBirthDate(LocalDate.now().plusDays(1));
		param.setIdCity(1L);

		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CustomerRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("birthDate", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CustomerRequestDTO_shouldHasConstraintViolationIdCityNull() {
		CustomerRequestDTO param = new CustomerRequestDTO();
		param.setName("nome");
		param.setSexo(Sexo.M);
		param.setBirthDate(LocalDate.now());

		Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CustomerRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("idCity", constraintViolation.getPropertyPath().toString());
	}

}
