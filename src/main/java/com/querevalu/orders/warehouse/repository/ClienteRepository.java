package com.querevalu.orders.warehouse.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.querevalu.orders.warehouse.entity.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	 List<Cliente> findByNombreContaining(String nombre);
	    Cliente findByEmail(String email);
	
}