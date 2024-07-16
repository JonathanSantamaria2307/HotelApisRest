package com.querevalu.orders.warehouse.validator;

import com.querevalu.orders.warehouse.entity.Reserva;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;

public class ReservaValidator {
    public static void save(Reserva reserva) {
        if(reserva.getCliente() == null) {
            throw new ValidateServiceException("Cliente es requerido");
        }
        if(reserva.getHabitacion() == null) {
            throw new ValidateServiceException("Habitacion es requerido");
        }
        if(reserva.getFechaInicio() == null) {
            throw new ValidateServiceException("Fecha inicio es requerido");
        }
        if(reserva.getFechaFin() == null) {
            throw new ValidateServiceException("Fecha fin es requerido");
        }
        if(reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
            throw new ValidateServiceException("Fecha inicio no puede ser posterior a fecha fin");
        }
    }
}