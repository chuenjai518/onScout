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

	public boolean checkAdminLogin(HttpSession session) {
		boolean login = false;
		if (session.getAttribute("username") != null) {
			if ((int) session.getAttribute("roleID") == 3) {
				login = true;
			}
		}
		return login;
	}
	@GetMapping("admin")
	public String index(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		if (!checkAdminLogin(session)) {
			return "redirect:/login";
		}
		List<UserInfo> userList = adminService.getAllUser();
		User user = new User();
		model.addAttribute("addUser", user);
		model.addAttribute("userList", userList);
		return "Admin/admin";
	}

	@PostMapping("admin/createUser")
	public RedirectView adminCreateUser(RedirectAttributes model, @ModelAttribute User user) {
		
		boolean exists = adminService.createUserProcess(user);
		if (exists) {
			model.addFlashAttribute("message", "Username is used!");
			return new RedirectView("/onScout/admin");
		}
		return new RedirectView("/onScout/admin");
	}

	@GetMapping("admin/delUser/{username}")
	public String adminDeleteUser(@PathVariable("username") String username, Model model) {
		adminService.disableUser(username);
		return "redirect:/admin";
	}
	@GetMapping("scouter/delUser/{username}")
	public String scouterDeleteUser(@PathVariable("username") String username, Model model) {
		adminService.disableUser(username);
		return "redirect:/scouter/scoutManage";
	}

}
