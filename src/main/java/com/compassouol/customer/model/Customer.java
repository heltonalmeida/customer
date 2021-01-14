package com.compassouol.customer.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.compassouol.customer.enums.Sexo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "dt_birth")
	private LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;
	
	@Column(name = "id_city")
	private Long idCity;

	@Builder
	public Customer(Long id, String name, LocalDate birthDate, Sexo sexo, Long idCity) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.idCity = idCity;
		this.sexo = sexo;
	}

}
