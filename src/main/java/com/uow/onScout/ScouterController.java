package com.uow.onScout;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uow.Model.User;
import com.uow.Model.UserInfo;

@Controller
public class ScouterController {
	@GetMapping("scouter")
	public String index(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		
		return "Scouter/scoutProcess";
	}
}
