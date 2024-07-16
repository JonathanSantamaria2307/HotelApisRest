package com.querevalu.orders.warehouse.converter;

import org.springframework.stereotype.Component;
import com.querevalu.orders.warehouse.dto.ReservaDto;
import com.querevalu.orders.warehouse.entity.Reserva;

@Component
public class ReservaConverter extends AbstractConverter<Reserva, ReservaDto> {
    @Override
    public ReservaDto fromEntity(Reserva entity) {
        if(entity == null) return null;
        return ReservaDto.builder()
                .id(entity.getId())
                .idCliente(entity.getCliente().getId())
                .idHabitacion(entity.getHabitacion().getId())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .estado(entity.getEstado())
                .build();
    }

    @Override
    public Reserva fromDTO(ReservaDto dto) {
        if(dto == null) return null;
        return Reserva.builder()
                .id(dto.getId())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .estado(dto.getEstado())
                .build();
    }
}