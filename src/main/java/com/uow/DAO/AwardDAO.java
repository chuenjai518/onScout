package com.uow.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.Task;
import com.uow.Model.TaskRowMapper;
import com.uow.Model.UserInfo;
import com.uow.Model.UserInfoRowMapper;

@Repository
public class AwardDAO {
	@Autowired
	private JdbcTemplate db;
	
	public List<Task> getPathfinderTask() {
		String sql = "SELECT * FROM Task where questID < 20000 and questID > 10000";
		RowMapper<Task> rowMapper = new TaskRowMapper();
		List<Task> taskList = db.query(sql, rowMapper);
		
		
		return taskList;
	}
	
	
}
