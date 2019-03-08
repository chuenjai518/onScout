package com.uow.onScout;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScoutController {
	@GetMapping("scoutProfile")
	public String index(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		
		return "Scout/scoutProfile";
	}

}
