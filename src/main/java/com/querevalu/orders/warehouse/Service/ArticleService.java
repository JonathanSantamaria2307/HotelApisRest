package com.querevalu.orders.warehouse.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.querevalu.orders.warehouse.entity.Article;

public interface ArticleService {
	public List<Article> findAll(Pageable page);
	public List<Article> findByName(String name, Pageable page);
	public Article findById(int id);
	public Article save(Article article);
	public Article update(Article article);
	public void delete(int id);
}
