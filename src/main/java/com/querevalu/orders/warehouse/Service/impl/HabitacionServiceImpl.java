package com.querevalu.orders.warehouse.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querevalu.orders.warehouse.entity.Habitacion;
import com.querevalu.orders.warehouse.exception.GeneralServiceException;
import com.querevalu.orders.warehouse.exception.NoDataFoundException;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;
import com.querevalu.orders.warehouse.repository.HabitacionRepository;
import com.querevalu.orders.warehouse.Service.HabitacionService;
import com.querevalu.orders.warehouse.validator.HabitacionValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionServiceImpl implements HabitacionService {
	@Autowired
	private HabitacionRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Habitacion findById(int id) {
		try {
			return repository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No se encontró la habitación con el ID: " + id));
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Habitacion save(Habitacion habitacion) {
		try {
			HabitacionValidator.save(habitacion);
			if (repository.findByTipo(habitacion.getTipo()) != null) {
				throw new ValidateServiceException("Ya existe una habitación con el tipo: " + habitacion.getTipo());
			}
			habitacion.setEstado("Disponible");
			return repository.save(habitacion);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Habitacion update(Habitacion habitacion) {
		try {
			HabitacionValidator.save(habitacion);
			Habitacion habitacionDB = repository.findById(habitacion.getId()).orElseThrow(
					() -> new NoDataFoundException("No se encontró la habitación con el ID: " + habitacion.getId()));
			Habitacion habitacionExistente = repository.findByTipo(habitacion.getTipo());
			if (habitacionExistente != null && habitacionExistente.getId() != habitacion.getId()) {
				throw new ValidateServiceException("Ya existe una habitación con el tipo: " + habitacion.getTipo());
			}
			habitacionDB.setTipo(habitacion.getTipo());
			habitacionDB.setPrecio(habitacion.getPrecio());
			habitacionDB.setEstado(habitacion.getEstado());
			return repository.save(habitacionDB);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Habitacion habitacion = repository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No se encontró la habitación con el ID: " + id));
			repository.delete(habitacion);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
