package com.uow.onScout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uow.Model.User;
import com.uow.Service.AdminService;

@Controller
public class UserController {
	
	@Autowired
	AdminService adminService;

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser(Model model) {
		
		List<User> list = adminService.getAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

}
