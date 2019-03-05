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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;

import com.uow.Model.User;
import com.uow.Service.AdminService;

@Controller
public class UserController {

	@Autowired
	AdminService adminService;

	@GetMapping("getAllUser")
	public ResponseEntity<List<User>> getAllUser(Model model) {

		List<User> list = adminService.getAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public String index(Model model) {
		return "redirect:/login";
	}

	@GetMapping("login")
	public String login(Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
//			// Scout
//			if (user.getRoleID() == 1) {
//				return return "General/login";;
//			} else
//			// Scouter
//			if (user.getRoleID() == 2) {
//				return return "General/login";;
//			} else
//			// Admin
//			{
//				return return "General/login";;
//			}
		}
		model.addAttribute("user", new User());
		return "General/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return "redirect:/login";
	}
		
	@PostMapping("forgetPassword")
	public String forgetPassword(Model model, @RequestParam String username) {
		adminService.forgetPassword(username);
		return "redirect:/login";
	}

	@PostMapping("loginProcess")
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
		return new RedirectView("login");

	}
	
	
}
