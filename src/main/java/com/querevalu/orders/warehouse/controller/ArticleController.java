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

import com.querevalu.orders.warehouse.entity.Article;
import com.querevalu.orders.warehouse.util.WrapperResponse;
import com.querevalu.orders.warehouse.Service.ArticleService;
import com.querevalu.orders.warehouse.converter.ArticleConverter;
import com.querevalu.orders.warehouse.dto.ArticleDto;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	@Autowired
	private ArticleService service;
	
	@Autowired
	private ArticleConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<ArticleDto>> findAll(
			@RequestParam(value = "name", required = false, defaultValue = "")String name,
			@RequestParam(value = "offset", required = false, defaultValue = "0")int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5")int pageSize){
		
			Pageable page = PageRequest.of(pageNumber,pageSize);
			List<Article> articles;
			articles = (name == null) ? service.findAll(page) : service.findByName(name, page);
			List<ArticleDto> articlesDto = converter.fromEntity(articles);
			return new WrapperResponse(true,"success",articlesDto).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity <WrapperResponse<ArticleDto>> findById(@PathVariable("id") int id){
		Article article = service.findById(id);
		ArticleDto articleDto = converter.fromEntity(article);
		return new WrapperResponse<ArticleDto>(true,"success",articleDto).createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity <ArticleDto> create(@RequestBody ArticleDto articleDto){
		Article record = service.save(converter.fromDTO(articleDto));
		ArticleDto recordDto = converter.fromEntity(record);
		return new WrapperResponse(true,"success",recordDto).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity <ArticleDto> update(@PathVariable("id") int id,
			@RequestBody ArticleDto articleDto){
		Article record = service.update(converter.fromDTO(articleDto));
		ArticleDto recordDto = converter.fromEntity(record);
		return new WrapperResponse(true,"success",recordDto).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity <ArticleDto> delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}