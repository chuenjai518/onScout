package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.ScoutDAO;
import com.uow.Model.EmerContact;
import com.uow.Model.User;
import com.uow.Model.UserInfo;

@Service
public class ScoutService {
	
	@Autowired
	ScoutDAO scoutDAO;

	public UserInfo getScout(String username){
		return scoutDAO.getScout(username);
	}
}
