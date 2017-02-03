package com.hanbit.hp.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	@RequestMapping("/form")
	public String form() {
		
		return "hello";	
	}
	
	@RequestMapping("api2/calc")  /* 파라미터에 HttpServletRequest request*/
	@ResponseBody
	public Map caculate(Model model,
			@RequestParam(name="operator", required=false) String operator,
			@RequestParam(name="right", required=false) String rightStr,
			@RequestParam(name="left", required=false) String leftStr ) {
		/*model.addAttribute("name","hello");*/
/* 오류 발생 처리
 * 		if ( request.getParameter("left") == null || 
				request.getParameter("right") == null){
			model.addAttribute("sum", 0);
			return "hello";
		}*/
		/*HttpServletRequest request 로 받았을때
	   *int left =0;
		int right =0;
		try {
			left= Integer.valueOf(request.getParameter("left"));
			right=Integer.valueOf(request.getParameter("right"));
		}
		catch (Exception e) {
			
		}*/
		int result=0;
		int left =0;
		int right =0;
		try {
			left = Integer.valueOf(leftStr);
			right = Integer.valueOf(rightStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if ("plus".equals(operator)){
			result=left+right;
		}
		else if ("minus".equals(operator)){
			result=left-right;
		}
		
		model.addAttribute("result", result);
		
		Map map =new HashMap();
		map.put("result", result);
		return map;
		
	}
	
	@RequestMapping("/")
	@ResponseBody
	public Map welcome(){
		Map welcom =new HashMap();
		
		welcom.put("msg", "hello welcom here");
		return welcom;
	}
	
	@RequestMapping("/api2/hello")
	@ResponseBody
	public List api(){
		List list = new ArrayList();
		list.add("Hanbit");
		list.add("Plate");
		list.add("API");
		
		return list;
	}

}
