package com.hanbit.hp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.hp.service.MainService;
import com.hanbit.hp.service.PickService;
import com.hanbit.hp.service.ToplistService;



@RestController
public class MainController {
	
	// @Autowired 널포인터 값 없게 끔 만들어줌
	//서비스에서 호출
	@Autowired
	private MainService mainService;
	@Autowired
	private ToplistService ToplistService;
	@Autowired
	private PickService pickService;
	
	@RequestMapping("/api2/main/imgs")
	public List<String> getMainImgs(){
			
		return mainService.getMainImgs();
	}
	// 파라미터 이름들이 같아야함 섹션코드들의 {}안에와 밑에 받아주는 스트링의 이름이 같아야함
	@RequestMapping("/api2/main/section/{sectionCode}/items")
	public List<Map<String, Object>> getSectionItems(@PathVariable String sectionCode) {
		
		if ("01".equals(sectionCode)){
			return ToplistService.getAll();
		}
		else if ("02".equals(sectionCode)){
			return pickService.getPicks(6);
		}
		return new ArrayList<>();
		
	}
	
	

}
