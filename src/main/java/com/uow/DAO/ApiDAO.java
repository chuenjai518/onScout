package com.uow.DAO;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID >= 10000 and questID < 20000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	public List<CompletedQuest> getSSAFinishDate(String username){
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID >= 20000 and questID < 30000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	public List<CompletedQuest> getSAAFinishDate(String username){
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID >= 30000 and questID < 40000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	public List<CompletedQuest> getCSAFinishDate(String username){
		String sql = "SELECT questID, FinishDate, username FROM CompletedQuest where username = ? and questID >= 40000 and questID < 50000";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	
	public List<CompletedQuest> getFinal(String username){
		String sql = "SELECT questID, FinishDate, username FROM onscout.CompletedQuest where (questID=10000 or  questID=11000 or questID=20000 or questID=21000 or questID=30000 or questID=31000 or questID=11400 or questID=11500 or questID=11600 or questID=21400 or questID=21500 or questID=21600 or questID=31400 or questID=31500 or questID=31600 or questID=41110 or questID=41120 or questID=41130 or questID=41210 or questID=41220 or questID=41230 or questID=41310 or questID=41320 or questID=41410 or questID=41420 or questID=41510 or questID=41520 or questID=41610 or questID=41620 or questID=42110 or questID=42120 or questID=42210 or questID=42220 or questID=42310 or questID=42410 or questID=42420 or questID=43110 or questID=43120 or questID=43130 or questID=43210 or questID=43220 or questID=43310 or questID=43320 or questID=44110 or questID=44210 or questID=44220 or questID=44310 or questID=44320 or questID=45100 or questID=45200) AND username = ?;";
		RowMapper<CompletedQuest> rowMapper = new CompletedQuestRowMapper();
		return this.db.query(sql, rowMapper, username);
	}
	
	public UserInfo getUserInfo(String username){
		String sql = "Select u.username, roleID, firstName, lastName, HKID, DOB, gender, address, phoneNum, email, region, district, scoutGroup, DOI from User u left join PersonalInfo p on u.username = p.username Where u.username = ? and disable = 0 and roleID = 1";
		RowMapper<UserInfo> rowMapper = new UserInfoRowMapper();
		try{
			return this.db.queryForObject(sql, rowMapper, username);
		} catch (EmptyResultDataAccessException e) {
			return new UserInfo();
		}
	}
	
	public boolean checkCSACompleted(String username) {
		boolean open = false;
		String sql = "Select count(*) From CompletedQuest where username = ? and questID = 40000";
		int count = db.queryForObject(sql, new Object[] { username }, Integer.class);
		if (count > 0) {
			open = true;
		}
		return open;
	}
}
