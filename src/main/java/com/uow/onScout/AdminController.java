package com.uow.onScout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uow.Service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("admin")
	public String index(Model model) {
		return "admin";
	}
	
	
}
