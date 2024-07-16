package com.querevalu.orders.warehouse.validator;

import com.querevalu.orders.warehouse.entity.Cliente;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;

public class ClienteValidator {
    public static void save(Cliente cliente) {
        if(cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new ValidateServiceException("Nombre es requerido");
        }
        if(cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new ValidateServiceException("Email es requerido");
        }
    }
}