package com.compassouol.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.compassouol.customer.dto.CustomerResponseDTO;
import com.compassouol.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT new com.compassouol.customer.dto.CustomerResponseDTO(c.id, c.name, c.birthDate, c.sexo, c.idCity) "
			+ "FROM Customer c "
			+ "WHERE ((:name IS NULL) OR (c.name LIKE %:name%))")
	Page<CustomerResponseDTO> findBy(@Param("name") String name, Pageable page);

}
