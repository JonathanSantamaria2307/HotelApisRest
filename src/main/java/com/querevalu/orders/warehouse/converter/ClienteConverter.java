package com.querevalu.orders.warehouse.converter;

import org.springframework.stereotype.Component;
import com.querevalu.orders.warehouse.dto.ClienteDto;
import com.querevalu.orders.warehouse.entity.Cliente;

@Component
public class ClienteConverter extends AbstractConverter<Cliente, ClienteDto> {
    @Override
    public ClienteDto fromEntity(Cliente entity) {
        if(entity == null) return null;
        return ClienteDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .build();
    }

    @Override
    public Cliente fromDTO(ClienteDto dto) {
        if(dto == null) return null;
        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .build();
    }
}