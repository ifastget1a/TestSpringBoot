package com.test.svc;

import java.util.List;

import com.test.svc.domain.Category;

public abstract class TestSvc {
	public abstract List<Category> retrieveDbRec(String cat_name);
}
