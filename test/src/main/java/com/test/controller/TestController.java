package com.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.svc.TestSvc;
import com.test.svc.domain.Category;

@Controller
@RequestMapping("/")
public class TestController {
	Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	@Qualifier("test.testSvc")
	private TestSvc testSvc;
	
	@RequestMapping("")
	public String index() {
		return "Index";
	}
	@GetMapping("show")
	public ModelAndView showWebpage(@RequestParam("catName") String cat_name) {
		List<Category> catList = testSvc.retrieveDbRec(cat_name);
	logger.info("catName: "+cat_name);
		ModelAndView mv = new ModelAndView("test");
		mv.addObject("catList", catList);
		return mv;
	}
	@GetMapping("api")
	public @ResponseBody List<Category> getJsonRsp(@RequestParam("catName") String cat_name) {
		return testSvc.retrieveDbRec(cat_name);
	}
}
