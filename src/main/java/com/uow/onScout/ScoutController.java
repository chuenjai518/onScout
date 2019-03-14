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
import com.uow.Service.ScoutService;

@Controller
public class ScoutController {
	@Autowired
	ScoutService scoutService;

	@GetMapping("scout")
	public String redirect(Model model, HttpSession session) {

		return "redirect:/scout/mainpage";
	}
	
	@GetMapping("scout/scoutProfile/{username}")
	public String scoutProfile(Model model, HttpSession session, @PathVariable("username") String username) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		// Need a model contain all scout information 
		// 
		UserInfo scoutInfo;
		scoutInfo = scoutService.getScoutInfo(username);
		model.addAttribute("scoutList", scoutInfo);
		return "scout/scoutProfile";
	}
	
	@GetMapping("scout/mainpage")
	public String mainpage(Model model, HttpSession session) {
		
		
		return "Scout/mainpage";
	}
	


	@GetMapping("scout/scoutPEdit/{username}")
	public String scoutPEdit(Model model, HttpSession session, @PathVariable("username") String username) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		// Need a model contain all scout information 
		UserInfo scoutInfo = scoutService.getScoutInfo(username);
		model.addAttribute("scoutList", scoutInfo);
		return "scout/scoutPEdit";
	}
}
