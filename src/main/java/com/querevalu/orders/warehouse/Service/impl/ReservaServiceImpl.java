package com.querevalu.orders.warehouse.Service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querevalu.orders.warehouse.entity.Reserva;
import com.querevalu.orders.warehouse.exception.GeneralServiceException;
import com.querevalu.orders.warehouse.exception.NoDataFoundException;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;
import com.querevalu.orders.warehouse.repository.ReservaRepository;
import com.querevalu.orders.warehouse.Service.ReservaService;
import com.querevalu.orders.warehouse.validator.ReservaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Reserva> findAll(Pageable page) {
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
    public Reserva findById(int id) {
        try {
            return repository.findById(id).orElseThrow(
                    () -> new NoDataFoundException("No se encontró la reserva con el ID: " + id));
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
    public Reserva save(Reserva reserva) {
        try {
            ReservaValidator.save(reserva);
            reserva.setEstado("Activa");
            return repository.save(reserva);
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
    public Reserva update(Reserva reserva) {
        try {
            ReservaValidator.save(reserva);
            Reserva reservaDB = repository.findById(reserva.getId()).orElseThrow(
                    () -> new NoDataFoundException("No se encontró la reserva con el ID: " + reserva.getId()));
            reservaDB.setCliente(reserva.getCliente());
            reservaDB.setHabitacion(reserva.getHabitacion());
            reservaDB.setFechaInicio(reserva.getFechaInicio());
            reservaDB.setFechaFin(reserva.getFechaFin());
            reservaDB.setEstado(reserva.getEstado());
            return repository.save(reservaDB);
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
            Reserva reserva = repository.findById(id).orElseThrow(
                    () -> new NoDataFoundException("No se encontró la reserva con el ID: " + id));
            repository.delete(reserva);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}