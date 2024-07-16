package com.querevalu.orders.warehouse.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.querevalu.orders.warehouse.entity.Reserva;
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	
	
	 List<Reserva> findByClienteId(int clienteId);
	    List<Reserva> findByHabitacionId(int habitacionId);
	    List<Reserva> findByEstado(String estado);
	    
}