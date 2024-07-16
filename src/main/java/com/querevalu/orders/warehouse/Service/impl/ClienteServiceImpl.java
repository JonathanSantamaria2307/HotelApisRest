package com.querevalu.orders.warehouse.Service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querevalu.orders.warehouse.entity.Cliente;
import com.querevalu.orders.warehouse.exception.GeneralServiceException;
import com.querevalu.orders.warehouse.exception.NoDataFoundException;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;
import com.querevalu.orders.warehouse.repository.ClienteRepository;
import com.querevalu.orders.warehouse.Service.ClienteService;
import com.querevalu.orders.warehouse.validator.ClienteValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll(Pageable page) {
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
    public Cliente findById(int id) {
        try {
            return repository.findById(id).orElseThrow(
                    () -> new NoDataFoundException("No se encontró el cliente con el ID: " + id));
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
    public Cliente save(Cliente cliente) {
        try {
            ClienteValidator.save(cliente);
            if (repository.findByEmail(cliente.getEmail()) != null) {
                throw new ValidateServiceException("Ya existe un cliente con el email: " + cliente.getEmail());
            }
            return repository.save(cliente);
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
    public Cliente update(Cliente cliente) {
        try {
            ClienteValidator.save(cliente);
            Cliente clienteDB = repository.findById(cliente.getId()).orElseThrow(
                    () -> new NoDataFoundException("No se encontró el cliente con el ID: " + cliente.getId()));
            Cliente clienteExistente = repository.findByEmail(cliente.getEmail());
            if (clienteExistente != null && clienteExistente.getId() != cliente.getId()) {
                throw new ValidateServiceException("Ya existe un cliente con el email: " + cliente.getEmail());
            }
            clienteDB.setNombre(cliente.getNombre());
            clienteDB.setEmail(cliente.getEmail());
            clienteDB.setTelefono(cliente.getTelefono());
            return repository.save(clienteDB);
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
            Cliente cliente = repository.findById(id).orElseThrow(
                    () -> new NoDataFoundException("No se encontró el cliente con el ID: " + id));
            repository.delete(cliente);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}