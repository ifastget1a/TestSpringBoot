package com.test.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.svc.dao.CategoryRepository;
import com.test.svc.domain.Category;

@Service("test.testSvc")
public class TestSvcImpl extends TestSvc {
	
	@Autowired
	@Qualifier("test.categoryDao")
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> retrieveDbRec(String cat_name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCatName(cat_name);

		//return categoryRepository.findAll();
	}

}
