package com.test.svc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.svc.domain.Category;

@Repository("test.categoryDao")
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	public List<Category> findByCatName(String catName);
	public List<Category> findAll();
}
