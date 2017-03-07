package com.hanbit.hp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.hp.service.JsonfileService;
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
	@Autowired
	private JsonfileService jsonfileService;
	
	@RequestMapping("/api2/main/imgs")
	public List<String> getMainImgs(){
			
		return mainService.getMainImgs();
	}
	// 파라미터 이름들이 같아야함 섹션코드들의 {}안에와 밑에 받아주는 스트링의 이름이 같아야함
	@RequestMapping("/api2/main/section/{sectionCode}/items")
	public List<Map<String, Object>> getSectionItems(@PathVariable String sectionCode) throws Exception {
		
		if ("01".equals(sectionCode)){
			return ToplistService.getAll();
		}
		else if ("02".equals(sectionCode)){
			return pickService.getPicks(6);
		}
		else if ("03".equals(sectionCode) || "04".equals(sectionCode) || "05".equals(sectionCode)){
			String filePath= "json/section"+sectionCode+".items.json";
			/*List.class 이렇게 하면 타입이 나옴*/
			return jsonfileService.getJsonFile(filePath, List.class);
		}
		return new ArrayList<>();
	}
	@RequestMapping("/api2/main/section/05/categories")
	public List getSectionCategory() throws Exception {
		String filePath ="json/section05.categories.json";
		return jsonfileService.getJsonFile(filePath, List.class);
	}
	@RequestMapping("/api2/common/hotplaces")
	public List getHotPlaces() throws Exception {
		String filePath ="json/common.hotplaces.json";
		return jsonfileService.getJsonFile(filePath, List.class);
	}
	
	

}
