package com.uow.onScout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.uow.Model.*;
import com.uow.Service.AdminService;
import com.uow.Service.ApiService;
import com.uow.Service.ScouterService;
@RestController
public class scoutProgressRestController {
	@Autowired
	ScouterService scouterService;
	@Autowired
	AdminService adminService;
	@Autowired
	ApiService apiService;
	
	@RequestMapping("scouter/getProgressJson")
	public List<UserInfo> userInfo() {
		List<UserInfo> userList = scouterService.getAllUser();
		return userList;
	}
	
	@RequestMapping("api/userInfo/{username}")
	public UserInfo getUserInfo(@PathVariable String username){
		
		return apiService.getUserInfo(username);
	}
	
	@RequestMapping("api/getSPAFinishDate/{username}")
	public List<CompletedQuest> getSPACompletedQuest(@PathVariable String username){
		
		return apiService.getSPAFinishDate(username);
	}
	
	@RequestMapping("api/getSSAFinishDate/{username}")
	public List<CompletedQuest> getSSACompletedQuest(@PathVariable String username){
		
		return apiService.getSSAFinishDate(username);
	}
	
	@RequestMapping("api/getSAAFinishDate/{username}")
	public List<CompletedQuest> getSAACompletedQuest(@PathVariable String username){
		
		return apiService.getSAAFinishDate(username);
	}
	
	@RequestMapping("api/getCSAFinishDate/{username}")
	public List<CompletedQuest> getCSACompletedQuest(@PathVariable String username){
		
		return apiService.getCSAFinishDate(username);
	}
	@RequestMapping("scouter/editCompletedQuest")
	public void editCompletedQuest(@RequestParam String username, @RequestParam int questID,
			@RequestParam String finishDate) {
		System.out.println(username + " " + questID + " " + finishDate);
		scouterService.editCompletedDate(username, questID, finishDate);
	}
	
	@RequestMapping("checkForm")
	public boolean checkForm(@RequestParam String username) {
		System.out.println(username);
		return apiService.checkCSACompleted(username);
	}
	
	@RequestMapping("changePassword")
	public void editCompletedQuest(@RequestParam String username, @RequestParam String password) {
		System.out.println(username + " " + password);
		adminService.changePassword(username, password);
	}
	
	@RequestMapping("scouter/totalNumOfAward")
	public List<UserInfo> totalNumOfAward(@RequestParam int questID) {
		List<UserInfo> userList = scouterService.showCompletedScout(questID);
		return userList;
	}
	
	
}
