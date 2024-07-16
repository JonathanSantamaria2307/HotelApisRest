package com.querevalu.orders.warehouse.Service;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.querevalu.orders.warehouse.entity.Cliente;
public interface ClienteService {
    public List<Cliente> findAll(Pageable page);
    public Cliente findById(int id);
    public Cliente save(Cliente cliente);
    public Cliente update(Cliente cliente);
    public void delete(int id);
}