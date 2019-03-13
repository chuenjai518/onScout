package com.uow.onScout;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScoutController {
	@GetMapping("scoutProfile")
	public String scoutProfile(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		// Need a model contain all scout information 
		// 
		return "Scout/scoutProfile";
	}
	
	@GetMapping("scout/mainpage")
	public String mainpage(Model model, HttpSession session) {
		
		
		return "Scout/mainpage";
	}

	@GetMapping("scoutPEdit")
	public String scoutPEdit(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		// Need a model contain all scout information 
		
		return "Scout/scoutPEdit";
	}
}
