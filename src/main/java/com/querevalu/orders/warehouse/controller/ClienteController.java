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

import com.querevalu.orders.warehouse.entity.Cliente;
import com.querevalu.orders.warehouse.util.WrapperResponse;
import com.querevalu.orders.warehouse.Service.ClienteService;
import com.querevalu.orders.warehouse.converter.ClienteConverter;
import com.querevalu.orders.warehouse.dto.ClienteDto;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
    private ClienteService service;
    
    @Autowired
    private ClienteConverter converter;
    
    @GetMapping()
    public ResponseEntity<List<ClienteDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Cliente> clientes = service.findAll(page);
        List<ClienteDto> clientesDto = converter.fromEntity(clientes);
        return new WrapperResponse(true, "success", clientesDto).createResponse(HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<ClienteDto>> findById(@PathVariable("id") int id) {
        Cliente cliente = service.findById(id);
        ClienteDto clienteDto = converter.fromEntity(cliente);
        return new WrapperResponse<ClienteDto>(true, "success", clienteDto).createResponse(HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto clienteDto) {
        Cliente record = service.save(converter.fromDTO(clienteDto));
        ClienteDto recordDto = converter.fromEntity(record);
        return new WrapperResponse(true, "success", recordDto).createResponse(HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable("id") int id,
            @RequestBody ClienteDto clienteDto) {
        Cliente record = service.update(converter.fromDTO(clienteDto));
        ClienteDto recordDto = converter.fromEntity(record);
        return new WrapperResponse(true, "success", recordDto).createResponse(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }
}
