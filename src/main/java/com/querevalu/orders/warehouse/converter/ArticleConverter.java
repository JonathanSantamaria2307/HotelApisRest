package com.querevalu.orders.warehouse.converter;

import org.springframework.stereotype.Component;

import com.querevalu.orders.warehouse.dto.ArticleDto;
import com.querevalu.orders.warehouse.entity.Article;

@Component
public class ArticleConverter extends AbstractConverter<Article,ArticleDto>{

	@Override
	public ArticleDto fromEntity(Article entities) {
		if(entities == null) return null;
		return ArticleDto.builder()
				.id(entities.getId())
				.name(entities.getName())
				.price(entities.getPrice())
				.build();
	}

	@Override
	public Article fromDTO(ArticleDto dto) {
		if(dto == null) return null;
		return Article.builder()
				.id(dto.getId())
				.name(dto.getName())
				.price(dto.getPrice())
				.build();
	}

}
