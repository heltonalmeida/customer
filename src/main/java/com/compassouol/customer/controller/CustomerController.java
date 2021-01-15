package com.compassouol.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.customer.dto.CustomerNameRequestDTO;
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
	
	private static final String SORT_BY_FIELD_ID = "id";
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "Recupera as informações do(s) cliente(s).")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
	@GetMapping
	public Page<CustomerResponseDTO> findBy(@RequestParam(required = false) String name,
			@PageableDefault(page = 0, size = 10, sort = SORT_BY_FIELD_ID, direction = Sort.Direction.ASC) final Pageable page) {
		return customerService.findBy(name, page);
	}
	
	@ApiOperation(value = "Realiza a consulta de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
	@GetMapping("/{id}")
	public CustomerResponseDTO findBy(@PathVariable Long id) {
		return customerService.findBy(id);
	}
	
	@ApiOperation(value = "Realiza a inserção de um cliente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Dados obrigatórios não informados | Tamanho do campo inválido | cidade não cadastrada"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
	@PostMapping
	public CustomerResponseDTO save(@Valid @RequestBody CustomerRequestDTO  customerRequestDTO) {
		return customerService.save(customerRequestDTO);
	}
	
	@ApiOperation(value = "Realiza a exclusão de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
	@DeleteMapping("/{id}")
	public MenssageResponseDTO delete(@PathVariable Long id) {
		customerService.delete(id);
		return MenssageResponseDTO.builder().menssage("Cliente deletado com sucesso").build();
	}
	
	@ApiOperation(value = "Realiza a alteração do nome do cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
	@PatchMapping("/{id}")
	public CustomerResponseDTO updateName(@PathVariable Long id, @Valid @RequestBody CustomerNameRequestDTO customerNameRequestDTO ) {
		return customerService.updateName(id, customerNameRequestDTO);
	}

}
