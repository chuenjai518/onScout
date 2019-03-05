package com.uow.onScout;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uow.Model.User;
import com.uow.Model.UserInfo;
import com.uow.Service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("admin")
	public String index(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		List<UserInfo> userList = adminService.getAllUser();
		model.addAttribute("userList", userList);
		return "Admin/admin";
	}

	@PostMapping("admin/createUser")
	public RedirectView adminCreateUser(RedirectAttributes model, @ModelAttribute User user) {
		boolean exists = adminService.createUserProcess(user);
		if (exists) {
			model.addFlashAttribute("message", "Username is used!");
			return new RedirectView("admin");
		}
		return new RedirectView("admin");
	}

	@GetMapping("admin/delUser/{username}")
	public String adminDeleteUser(@PathVariable("username") String username, Model model) {
		adminService.disableUser(username);
		return "redirect:/admin";
	}

}
