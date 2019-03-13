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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uow.Model.EmerContact;
import com.uow.Model.ScoutManage;
import com.uow.Model.User;
import com.uow.Model.UserInfo;
import com.uow.Service.ScouterService;

@Controller
public class ScouterController {
	@Autowired
	ScouterService scouterService;
	
	@GetMapping("scouter/")
	public String redirect(Model model) {
		return "redirect:/scouter/scoutProcess";
	}
	
	@GetMapping("scouter/scoutProcess")
	public String index(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		//
		// I need a model scoutList that contain 
		// -getLastName() - getFirstName() - getGender() - getLatestMission() - getScoutID()
		//
		List<UserInfo> userList = scouterService.getAllUser();
		model.addAttribute("userList", userList);
		return "Scouter/scoutProcess";
	}
	
	@GetMapping("scouter/scoutProcess/{scoutID}")
	public String scoutProcess(@PathVariable("scoutID") Integer scoutID, Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		List<UserInfo> userList = scouterService.getAllUser();
		model.addAttribute("userList", userList);
		return "Scouter/scoutProcess";
	}
	
	@GetMapping("scouter/scoutManage")
	public String scoutManage(Model model, HttpSession session) {


		List<ScoutManage> userList = scouterService.getScoutManageList();
		model.addAttribute("userList", userList);
		return "Scouter/scoutManage";
	}
	
	@GetMapping("scouter/scoutManage/{username}")
	public String scoutManageDetail(@PathVariable("username") String username, Model model, HttpSession session) {
		List<EmerContact> emerList = scouterService.getEmerContact(username);
		UserInfo userInfo = scouterService.getScoutInfo(username);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("emerList", emerList);
		return "Scouter/scoutManage";
	}
	
	@GetMapping("scouter/viewAw")
	public String viewAw(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		List<UserInfo> userList = scouterService.getAllUser();
		model.addAttribute("userList", userList);
		return "Scouter/viewAw";
	}

	@GetMapping("scouter/editProfile")
	public String editProfile(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		List<UserInfo> userList = scouterService.getAllUser();
		model.addAttribute("userList", userList);
		return "Scouter/editProfile";
	}
	
	@PostMapping("scouter/createUser")
	public RedirectView forgetPassword(RedirectAttributes model, @RequestParam String username) {
		boolean exists = scouterService.createUserProcess(username);
		if (exists) {
			model.addFlashAttribute("message", "Username is used!");
			return new RedirectView("/onScout/scouter#addUser");
		}
		return new RedirectView("/onScout/scoutManage");
	}
	

	
	
}