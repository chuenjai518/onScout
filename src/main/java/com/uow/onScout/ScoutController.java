package com.uow.onScout;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uow.Model.User;
import com.uow.Service.ScouterService;
import com.uow.Model.ScoutInfo;
import com.uow.Model.ScoutInfoRowMapper;
import com.uow.Service.ScoutService;

@Controller
public class ScoutController {
	
	ScoutService scoutService;
	
	@GetMapping("scoutProfile")
	public String scoutProfile(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		// Need a model contain all scout information 
		// 
		List<ScoutInfo> scout = scoutService.getAllScout();
		model.addAttribute("scoutList", scout);
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
		List<ScoutInfo> scout = scoutService.getAllScout();
		model.addAttribute("scoutList", scout);
		return "Scout/scoutPEdit";
	}
}
