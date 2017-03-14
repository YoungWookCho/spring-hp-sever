package com.hanbit.hp.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("")
	public String main(Model model) {
		model.addAttribute("cssName", "main");
		model.addAttribute("jsName", "main");
		
		return "admin/index";
	}
	
	@RequestMapping("/{menuId}")
	public String menu(Model model, @PathVariable("menuId") String menuId) {
		model.addAttribute("menuId", menuId);
		
		String cssName = "main";
		
		if ("store".equals(menuId)) {
			cssName = menuId;
		}
		
		model.addAttribute("cssName", cssName);
		model.addAttribute("jsName", menuId);
		
		return "admin/" + menuId;
	}
	
}
