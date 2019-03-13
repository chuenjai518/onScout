package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.ScoutDAO;
import com.uow.Model.EmerContact;
import com.uow.Model.ScoutInfo;
import com.uow.Model.User;

@Service
public class ScoutService {
	
	@Autowired
	ScoutDAO scoutDAO;

	public List<ScoutInfo> getAllScout(){
		return scoutDAO.getAllScout();
	}
}
