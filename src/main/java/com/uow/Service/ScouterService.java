package com.uow.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.ScouterDAO;
import com.uow.Model.EmerContact;
import com.uow.Model.ScoutManage;
import com.uow.Model.User;
import com.uow.Model.UserInfo;

@Service
public class ScouterService {
	@Autowired
	ScouterDAO scouterDAO;
	
	public List<UserInfo> getAllUser(){
		return scouterDAO.getAllUser();
	}
	
	public UserInfo getScoutInfo(String username) {
		return scouterDAO.getScoutInfo(username);
	}
	
	public List<ScoutManage> getScoutManageList(){
		return scouterDAO.getScoutManageList();
	}
	
	public List<EmerContact> getEmerContact(String username){
		return scouterDAO.getEmerContact(username);
	}
	
	
	public boolean createUserProcess(String username) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(username);
		user.setRoleID(1);
		return scouterDAO.createUserProcess(user);
	}
	
	public UserInfo getScouterInfo(String username) {
		return scouterDAO.getScouterInfo(username);
	}
	
	public void editProfileProcess(String username, String email, int phoneNum) {
		scouterDAO.editProfileProcess(username, email, phoneNum);
	}
	
	public void editScoutProfileProcess(String username, UserInfo userinfo) {
		scouterDAO.editScoutProfileProcess(username, userinfo);
	}
	
	public void editCompletedDate(String username, int questID, Date finishDate) {
		scouterDAO.editCompletedDate(username, questID, finishDate);
	}
}
