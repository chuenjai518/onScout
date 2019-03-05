package com.uow.onScout;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uow.Model.User;
import com.uow.Service.AdminService;

@Controller
public class UserController {

	@Autowired
	AdminService adminService;

	@GetMapping("/")
	public String login(Model model) {
<<<<<<< HEAD
		model.addAttribute("user", new User());
		return "login";
=======
		return "General/login";
>>>>>>> 6bfb1097a6c87d6d04bcb979ffc6a04770a72187
	}
	
	@GetMapping("/forgetPassword")
	public String forgetPassword(Model model) {
		return "forgetPassword";
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser(Model model) {

		List<User> list = adminService.getAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// Need change to Post
	@PostMapping("/loginProcess")
	public RedirectView loginProcess(@ModelAttribute User temp, RedirectAttributes model, HttpSession session) {
		User user = adminService.loginProcess(temp);

		if (user == null) {
			model.addFlashAttribute("message", "Incorrect username or password!");
			return new RedirectView("login");
		}
		session.setAttribute("user", user);

//		// Scout
//		if (user.getRoleID() == 1) {
//			return new RedirectView("driverPage");
//		} else
//		// Scouter
//		if (user.getRoleID() == 2) {
//			return new RedirectView("admin");
//		} else
//		// Admin
//		{
//			return new RedirectView("carparkOwner");
//		}
		return new RedirectView("/");

	}
	
	
}
