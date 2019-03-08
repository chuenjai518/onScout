package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.ScouterDAO;
import com.uow.Model.UserInfo;

@Service
public class ScouterService {
	@Autowired
	ScouterDAO scouterDAO;
	
	public List<UserInfo> getAllUser(){
		return scouterDAO.getAllUser();
	}
	
	public boolean createUser(String username, int roleID) {
		
		return true;
	}
	
}
