package com.uow.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.ScoutDAO;
import com.uow.Model.User;

@Service
public class ScoutController {
	
	@Autowired
	ScoutDAO scoutDAO;


}
