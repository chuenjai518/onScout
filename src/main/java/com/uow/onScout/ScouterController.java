package com.uow.onScout;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uow.Model.User;
import com.uow.Model.UserInfo;
import com.uow.Service.ScouterService;

@Controller
public class ScouterController {
	@Autowired
	ScouterService scouterService;
	
	@GetMapping("scouter")
	public String index(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		List<UserInfo> userList = scouterService.getAllUser();
		model.addAttribute("userList", userList);
		return "Scouter/scoutProcess";
	}
	
	@PostMapping("scouter/createUser")
	public RedirectView forgetPassword(RedirectAttributes model, @RequestParam int roleID,  @RequestParam String username) {
		System.out.println(roleID + username);
		//scouterService.createUser(username, roleID);
		model.addFlashAttribute("message", "username has been used!");
		return new RedirectView("/onScout/scouter#addUser");
	}
	
	
}