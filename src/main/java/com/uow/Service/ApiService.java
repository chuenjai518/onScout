package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.ApiDAO;
import com.uow.Model.CompletedQuest;

@Service
public class ApiService {
	@Autowired
	ApiDAO apiDAO;
	
	public List<CompletedQuest> getCompletedQuest(String username){
		return apiDAO.getCompletedQuest(username);
	}
}
