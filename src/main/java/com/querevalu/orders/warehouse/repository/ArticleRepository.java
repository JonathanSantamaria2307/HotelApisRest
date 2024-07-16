package com.querevalu.orders.warehouse.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.querevalu.orders.warehouse.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{
	List<Article> findByNameContaining(String name, Pageable page);
	Article findByName(String name);
}
