package com.compassouol.customer.city.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compassouol.customer.city.dto.CityResponseDTO;

@FeignClient(name = "cityFeign", url = "${city.baseURL}")
public interface CityClientFeign {

	@GetMapping("/{id}")	
    CityResponseDTO findBy(@PathVariable Long id);

}