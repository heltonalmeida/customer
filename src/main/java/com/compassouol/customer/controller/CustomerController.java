package com.compassouol.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.customer.dto.CustomerRequestDTO;
import com.compassouol.customer.dto.CustomerResponseDTO;
import com.compassouol.customer.dto.MenssageResponseDTO;
import com.compassouol.customer.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "Realiza a consulta de um cliente",
            consumes = "application/json",
            produces = "application/json",
            httpMethod = "GET",
            response = CustomerResponseDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
	@GetMapping("/{id}")
	public CustomerResponseDTO findBy(@PathVariable Long id) {
		return customerService.findBy(id);
	}
	
	@ApiOperation(value = "Realiza a inserção de um cliente.",
            consumes = "application/json",
            produces = "application/json",
            httpMethod = "POST",
            response = CustomerResponseDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Dados obrigatórios não informados | Tamanho do campo inválido | cidade não cadastrada"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
	@PostMapping
	public CustomerResponseDTO save(@Valid @RequestBody CustomerRequestDTO  customerRequestDTO) {
		return customerService.save(customerRequestDTO);
	}
	
	@ApiOperation(value = "Realiza a exclusão de um cliente",
            consumes = "application/json",
            produces = "application/json",
            httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
	@DeleteMapping("/{id}")
	public MenssageResponseDTO delete(@PathVariable Long id) {
		customerService.delete(id);
		return MenssageResponseDTO.builder().menssage("Cliente deletado com sucesso").build();
	}
	
	
	

}
