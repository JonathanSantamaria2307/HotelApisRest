package com.querevalu.orders.warehouse.validator;

import com.querevalu.orders.warehouse.entity.Article;
import com.querevalu.orders.warehouse.exception.ValidateServiceException;

public class ArticleValidator {
	public static void save(Article article) {
		if(article.getName() == null || article.getName().isEmpty()) {
			throw new ValidateServiceException("Name is required");
		}
		if(article.getName().length() > 100) {
			throw new ValidateServiceException("The name must be a maximum of 100 characters");
		}
		if(article.getPrice() == null) {
			throw new ValidateServiceException("Price is required");
		}
		if(article.getPrice() < 0) {
			throw new ValidateServiceException("Incorrect price");
		}
	}
}
