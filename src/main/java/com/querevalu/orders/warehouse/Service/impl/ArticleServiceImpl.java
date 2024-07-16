package com.querevalu.orders.warehouse.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querevalu.orders.warehouse.entity.Article;
import com.querevalu.orders.warehouse.exception.GeneralServiceException;
import com.querevalu.orders.warehouse.exception.NoDataFoundException;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;
import com.querevalu.orders.warehouse.repository.ArticleRepository;
import com.querevalu.orders.warehouse.Service.ArticleService;
import com.querevalu.orders.warehouse.validator.ArticleValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Article> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Article> findByName(String name, Pageable page) {
		try {
			return repository.findByNameContaining(name,page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Article findById(int id) {
		try {
			Article record = repository.findById(id).orElseThrow(
					()->new NoDataFoundException("There is no record with this ID"));
			return record;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Article save(Article article) {
		try {
			ArticleValidator.save(article);
			if(repository.findByName(article.getName()) != null) {
				throw new ValidateServiceException("There is already an article with the name: " + article.getName());
			}
			article.setState(true);
			Article record = repository.save(article);
			return record;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Article update(Article article) {
		try {
			ArticleValidator.save(article);
			Article record = repository.findById(article.getId()).orElseThrow(
					()->new NoDataFoundException("There is no record with this ID"));
			Article duplicate = repository.findByName(article.getName());
			if(duplicate != null && duplicate.getId() != article.getId()) {
				throw new ValidateServiceException("There is already an article with the name: " + article.getName());
			}
			record.setName(article.getName());
			record.setPrice(article.getPrice());
			repository.save(record);
			return record;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Article record = repository.findById(id).orElseThrow(
					()->new NoDataFoundException("There is no record with this ID"));
			repository.delete(record);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

}
