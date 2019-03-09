package com.uow.onScout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uow.Model.*;
import com.uow.Service.ScouterService;
@RestController
public class scoutProgressRestController {
	@Autowired
	ScouterService scouterService;
	@RequestMapping("scouter/getProgressJson")
	public List<UserInfo> userInfo() {
		List<UserInfo> userList = scouterService.getAllUser();
		return userList;
	}
}
