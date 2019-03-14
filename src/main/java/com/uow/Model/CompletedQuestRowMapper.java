package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class CompletedQuestRowMapper implements RowMapper<CompletedQuest> {

	@Override
	public CompletedQuest mapRow(ResultSet row, int rowNum) throws SQLException {
		CompletedQuest cq = new CompletedQuest();
		cq.setUsername(row.getString("username"));
		cq.setQuestID(row.getInt("questID"));
		cq.setFinishDate(row.getDate("FinishDate"));
		return cq;
	}

}
