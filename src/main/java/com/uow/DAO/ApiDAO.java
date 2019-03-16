package com.uow.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.CompletedQuest;
import com.uow.Model.CompletedQuestRowMapper;
import com.uow.Model.UserInfo;
import com.uow.Model.UserInfoRowMapper;

@Repository
public class ApiDAO {
	@Autowired
	private JdbcTemplate db;
	
	public List<CompletedQuest> getSPAFinishDate(String username){
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID > 10000 and questID < 20000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	public List<CompletedQuest> getSSAFinishDate(String username){
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID > 20000 and questID < 30000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	public List<CompletedQuest> getSAAFinishDate(String username){
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID > 30000 and questID < 40000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	public List<CompletedQuest> getCSAFinishDate(String username){
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID > 40000 and questID < 50000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
}
