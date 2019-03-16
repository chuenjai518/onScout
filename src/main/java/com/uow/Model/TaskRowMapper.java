package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet row, int rowNum) throws SQLException {
		Task task = new Task();
		task.setTaskID(row.getInt("taskID"));
		task.setCategoryID(row.getInt("cateforyID"));
		task.setTaskDesc(row.getString("taskDesc"));
		task.setQuestID(row.getInt("questID"));
		task.setSubTaskNum(row.getInt("subTaskNum"));
		
		return task;
	}

}