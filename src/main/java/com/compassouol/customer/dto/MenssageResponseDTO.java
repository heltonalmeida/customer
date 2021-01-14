package com.compassouol.customer.dto;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class MenssageResponseDTO {

	private Integer status;
	private String menssage;

	@Builder
	public MenssageResponseDTO(Integer status, String menssage) {
		this.status = Objects.isNull(status) ? HttpStatus.OK.value() : status;
		this.menssage = menssage;
	}

}
