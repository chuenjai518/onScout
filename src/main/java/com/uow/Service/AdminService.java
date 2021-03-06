package com.uow.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.AdminDAO;
import com.uow.Model.User;
import com.uow.Model.UserInfo;

@Service
public class AdminService {
	
	@Autowired
	AdminDAO adminDAO;
	
	public List<UserInfo> getAllUser(){
		return adminDAO.getAllUser();
	}
	
	public User loginProcess(User user) {
		return adminDAO.loginProcess(user);
	}
	
	public void forgetPassword(String username) {
		adminDAO.forgetPassword(username);
	}

	public boolean createUserProcess(User user) {
		return adminDAO.createUserProcess(user);
	}
	
	public void disableUser(String username) {
		adminDAO.disableUser(username);
	}
	
	public void changePassword(String username, String password) {
		adminDAO.changePassword(username, password);
	}
	
}
