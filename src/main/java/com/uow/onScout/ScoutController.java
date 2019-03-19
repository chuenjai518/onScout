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
	
	public boolean checkScoutLogin(HttpSession session) {
		boolean login = false;
		if (session.getAttribute("username") != null) {
			if ((int) session.getAttribute("roleID") == 1) {
				login = true;
			}
		}
		return login;
	}
	
	@GetMapping("scout/scoutProfile")
	public String scoutProfile(Model model, HttpSession session) {
		// model.addAttribute("user", );
		// Need a model contain all scout information 
		// 
		
		if (!checkScoutLogin(session)) {
			return "redirect:/login";
		}
		String username = (String)session.getAttribute("username");
		UserInfo scoutInfo = scoutService.getScoutInfo(username);
		model.addAttribute("scoutInfo", scoutInfo);
		//Need Emergency contact
		//Also need post mapping to change form information: Email and phone no. need change
		List<EmerContact> emerContact = scoutService.getEmerContact(username);
		model.addAttribute("emerContact", emerContact);

		UserInfo scoutNavInfo = scoutService.getScoutNavInfo(username);
		model.addAttribute("scoutNavInfo",scoutNavInfo);
		
		return "scout/scoutProfile";
	}
	
	@PostMapping("scout/scoutProfile")
	public String editScoutProfileProcess( Model model, HttpSession session, @ModelAttribute UserInfo userInfo) {
		String username = (String)session.getAttribute("username");
		scoutService.updateScoutInfo(userInfo.getEmail(), userInfo.getPhoneNum(), username);
		System.out.println(userInfo.getEmail());
		return "redirect:/scout/scoutProfile";
	}
	
	@GetMapping("scout/mainpage")
	public String mainpage(Model model, HttpSession session) {
		
		if (!checkScoutLogin(session)) {
			return "redirect:/login";
		}
		model.addAttribute("openSSA", true);
		model.addAttribute("openSAA", true);
		model.addAttribute("openCSA", true);
		model.addAttribute("username", (String)session.getAttribute("username"));
		

		String username = (String)session.getAttribute("username");
		UserInfo scoutNavInfo = scoutService.getScoutNavInfo(username);
		model.addAttribute("scoutNavInfo",scoutNavInfo);
		
		return "scout/mainpage";
	}
	

}
