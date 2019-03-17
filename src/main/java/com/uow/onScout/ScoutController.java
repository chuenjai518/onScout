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

import com.uow.Model.EmerContact;
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
		UserInfo scoutInfo = scoutService.getScoutInfo(username);
		model.addAttribute("scoutInfo", scoutInfo);
		//Need Emergency contact
		//Also need post mapping to change form information: Email and phone no. need change
		List<EmerContact> emerContact = scoutService.getEmerContact(username);
		model.addAttribute("emerContact", emerContact);
		return "scout/scoutProfile";
	}
	
	@PostMapping("scout/scoutProfile/{username}")
	public String editScoutProfileProcess(@PathVariable("username") String username, Model model, HttpSession session, @ModelAttribute UserInfo userInfo) {
		scoutService.updateScoutInfo(userInfo.getEmail(), userInfo.getPhoneNum(), username);
		System.out.println(userInfo.getEmail());
		return "redirect:/scout/scoutProfile/{username}";
	}
	
	@GetMapping("scout/mainpage")
	public String mainpage(Model model, HttpSession session) {
		
		
		return "scout/mainpage";
	}
	

	@GetMapping("scout/scoutNav")
	public String scoutNav(Model model, HttpSession session) {
		//Need Scout Full Name and Unit
		UserInfo scoutNavInfo = scoutService.getScoutNavInfo((String)session.getAttribute("username"));
		model.addAttribute("scoutNavInfo",scoutNavInfo);
		return "scout/scoutNav";
	}

}
