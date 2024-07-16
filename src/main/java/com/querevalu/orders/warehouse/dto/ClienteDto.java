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
public class ClienteDto {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
}