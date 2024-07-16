package com.querevalu.orders.warehouse.converter;

import org.springframework.stereotype.Component;
import com.querevalu.orders.warehouse.dto.HabitacionDto;
import com.querevalu.orders.warehouse.entity.Habitacion;

@Component
public class HabitacionConverter extends AbstractConverter<Habitacion, HabitacionDto> {
    @Override
    public HabitacionDto fromEntity(Habitacion entity) {
        if(entity == null) return null;
        return HabitacionDto.builder()
                .id(entity.getId())
                .tipo(entity.getTipo())
                .precio(entity.getPrecio())
                .estado(entity.getEstado())
                .build();
    }

    @Override
    public Habitacion fromDTO(HabitacionDto dto) {
        if(dto == null) return null;
        return Habitacion.builder()
                .id(dto.getId())
                .tipo(dto.getTipo())
                .precio(dto.getPrecio())
                .estado(dto.getEstado())
                .build();
    }
}