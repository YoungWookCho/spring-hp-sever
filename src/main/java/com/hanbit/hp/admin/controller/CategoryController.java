package com.hanbit.hp.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.hp.admin.service.CategoryService;


@RestController
@RequestMapping("/admin/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/list")
	public List list() {
		
		return categoryService.getList();
	}
	@RequestMapping(value="/{categoryId}", method= RequestMethod.GET)
	public Map get(@PathVariable("categoryId") String categoryId) {
		return categoryService.get(categoryId);
	}
	@RequestMapping(value="/{categoryId}", method= RequestMethod.PUT)
	public Map modify(@PathVariable("categoryId") String categoryId,
			@RequestParam("categoryName") String categoryName) {
		categoryService.modify(categoryId, categoryName);
	
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	@RequestMapping(value="/{categoryId}", method= RequestMethod.DELETE)
	public Map remove(@PathVariable("categoryId") String categoryId) {
		categoryService.remove(categoryId);
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	
	@RequestMapping("/add")
	public Map add(@RequestParam("categoryName") String categoryName) {
		categoryService.add(categoryName);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	
}
