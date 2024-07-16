package com.querevalu.orders.warehouse.Service;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.querevalu.orders.warehouse.entity.Habitacion;
public interface HabitacionService {
    public List<Habitacion> findAll(Pageable page);
    public Habitacion findById(int id);
    public Habitacion save(Habitacion habitacion);
    public Habitacion update(Habitacion habitacion);
    public void delete(int id);
}