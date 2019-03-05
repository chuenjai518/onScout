package com.uow.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.AdminDAO;
import com.uow.Model.User;

@Service
public class AdminService {
	
	@Autowired
	AdminDAO adminDAO;
	
	public List<User> getAllUser(){
		return adminDAO.getAllUser();
	}
	
	public User loginProcess(User user) {
		return adminDAO.loginProcess(user);
	}
	
	public void forgetPassword(String username) {
		adminDAO.forgetPassword(username);
	}

	
}
