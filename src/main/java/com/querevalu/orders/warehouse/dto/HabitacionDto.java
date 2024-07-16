package com.querevalu.orders.warehouse.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class HabitacionDto {
    private int id;
    private String tipo;
    private Double precio;
    private String estado;
}