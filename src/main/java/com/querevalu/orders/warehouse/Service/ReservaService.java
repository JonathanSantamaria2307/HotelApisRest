package com.querevalu.orders.warehouse.Service;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.querevalu.orders.warehouse.entity.Reserva;
public interface ReservaService {
    public List<Reserva> findAll(Pageable page);
    public Reserva findById(int id);
    public Reserva save(Reserva reserva);
    public Reserva update(Reserva reserva);
    public void delete(int id);
}