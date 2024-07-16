package com.querevalu.orders.warehouse.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querevalu.orders.warehouse.entity.Habitacion;
import com.querevalu.orders.warehouse.util.WrapperResponse;
import com.querevalu.orders.warehouse.Service.HabitacionService;
import com.querevalu.orders.warehouse.converter.HabitacionConverter;
import com.querevalu.orders.warehouse.dto.HabitacionDto;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {
	@Autowired
    private HabitacionService service;
    
    @Autowired
    private HabitacionConverter converter;
    
    @GetMapping()
    public ResponseEntity<List<HabitacionDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Habitacion> habitaciones = service.findAll(page);
        List<HabitacionDto> habitacionesDto = converter.fromEntity(habitaciones);
        return new WrapperResponse(true, "success", habitacionesDto).createResponse(HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<HabitacionDto>> findById(@PathVariable("id") int id) {
        Habitacion habitacion = service.findById(id);
        HabitacionDto habitacionDto = converter.fromEntity(habitacion);
        return new WrapperResponse<HabitacionDto>(true, "success", habitacionDto).createResponse(HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<HabitacionDto> create(@RequestBody HabitacionDto habitacionDto) {
        Habitacion record = service.save(converter.fromDTO(habitacionDto));
        HabitacionDto recordDto = converter.fromEntity(record);
        return new WrapperResponse(true, "success", recordDto).createResponse(HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<HabitacionDto> update(@PathVariable("id") int id,
            @RequestBody HabitacionDto habitacionDto) {
        Habitacion record = service.update(converter.fromDTO(habitacionDto));
        HabitacionDto recordDto = converter.fromEntity(record);
        return new WrapperResponse(true, "success", recordDto).createResponse(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HabitacionDto> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }
}
