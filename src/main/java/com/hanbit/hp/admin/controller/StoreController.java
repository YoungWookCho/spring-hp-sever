package com.hanbit.hp.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hanbit.hp.admin.service.StoreService;
import com.hanbit.hp.aop.LoggingAspect;

@RestController
@RequestMapping("/admin/api/store")
public class StoreController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);
	
	@Autowired
	private StoreService storeService;
	/* MultipartHttpServletRequest 폼데이터에 여라가지 받을때*/
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public Map add(MultipartHttpServletRequest request) {
		
		String storeName =request.getParameter("storeName");
		String categoryId =request.getParameter("categoryId");
		String locationId =request.getParameter("locationId");
		MultipartFile storeImgFile=request.getFile("storeImg");
		
		storeService.add(storeName, categoryId, locationId, storeImgFile);
		
		Map result = new HashMap();
		result.put("result", "ok");
		return result;
	}

}
