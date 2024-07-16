package com.querevalu.orders.warehouse.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ReservaDto {
	private int id;
    private int idCliente;
    private int idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
}
