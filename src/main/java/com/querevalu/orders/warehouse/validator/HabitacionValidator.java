package com.querevalu.orders.warehouse.validator;

import com.querevalu.orders.warehouse.entity.Habitacion;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;

public class HabitacionValidator {
    public static void save(Habitacion habitacion) {
        if(habitacion.getTipo() == null || habitacion.getTipo().isEmpty()) {
            throw new ValidateServiceException("Tipo es requerido");
        }
        if(habitacion.getPrecio() == null) {
            throw new ValidateServiceException("Precio es requerido");
        }
        if(habitacion.getPrecio() < 0) {
            throw new ValidateServiceException("precio incorrecto");
        }
    }
}