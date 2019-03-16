package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.ScoutDAO;
import com.uow.Model.EmerContact;
import com.uow.Model.UserInfo;

@Service
public class ScoutService {
	
	@Autowired
	ScoutDAO scoutDAO;

	public UserInfo getScoutInfo(String username){
		return scoutDAO.getScoutInfo(username);
	}
	
	public List<EmerContact> getEmerContact(String username){
		return scoutDAO.getEmerContact(username);
	}
	
	public void updateScoutInfo(String email, int phoneNum, String username) {
		scoutDAO.updateScoutInfo(email, phoneNum, username);
	}
}
