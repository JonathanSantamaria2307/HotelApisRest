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

import com.querevalu.orders.warehouse.entity.Reserva;
import com.querevalu.orders.warehouse.util.WrapperResponse;
import com.querevalu.orders.warehouse.Service.ReservaService;
import com.querevalu.orders.warehouse.converter.ReservaConverter;
import com.querevalu.orders.warehouse.dto.ReservaDto;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

	 @Autowired
	    private ReservaService service;
	    
	    @Autowired
	    private ReservaConverter converter;
	    
	    @GetMapping()
	    public ResponseEntity<List<ReservaDto>> findAll(
	            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
	            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
	        
	        Pageable page = PageRequest.of(pageNumber, pageSize);
	        List<Reserva> reservas = service.findAll(page);
	        List<ReservaDto> reservasDto = converter.fromEntity(reservas);
	        return new WrapperResponse(true, "success", reservasDto).createResponse(HttpStatus.OK);
	    }
	    
	    @GetMapping(value = "/{id}")
	    public ResponseEntity<WrapperResponse<ReservaDto>> findById(@PathVariable("id") int id) {
	        Reserva reserva = service.findById(id);
	        ReservaDto reservaDto = converter.fromEntity(reserva);
	        return new WrapperResponse<ReservaDto>(true, "success", reservaDto).createResponse(HttpStatus.OK);
	    }
	    
	    @PostMapping
	    public ResponseEntity<ReservaDto> create(@RequestBody ReservaDto reservaDto) {
	        Reserva record = service.save(converter.fromDTO(reservaDto));
	        ReservaDto recordDto = converter.fromEntity(record);
	        return new WrapperResponse(true, "success", recordDto).createResponse(HttpStatus.CREATED);
	    }
	    
	    @PutMapping(value = "/{id}")
	    public ResponseEntity<ReservaDto> update(@PathVariable("id") int id,
	            @RequestBody ReservaDto reservaDto) {
	        Reserva record = service.update(converter.fromDTO(reservaDto));
	        ReservaDto recordDto = converter.fromEntity(record);
	        return new WrapperResponse(true, "success", recordDto).createResponse(HttpStatus.OK);
	    }
	    
	    @DeleteMapping(value = "/{id}")
	    public ResponseEntity<ReservaDto> delete(@PathVariable("id") int id) {
	        service.delete(id);
	        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
	    }
	}