package com.querevalu.orders.warehouse.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.querevalu.orders.warehouse.entity.Habitacion;
@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
	
	  List<Habitacion> findByTipoContaining(String tipo);
	    List<Habitacion> findByEstado(String estado);
	    Habitacion findByTipo(String tipo);
}