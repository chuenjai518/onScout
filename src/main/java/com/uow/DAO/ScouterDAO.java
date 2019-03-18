package com.uow.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.EmerContact;
import com.uow.Model.EmerContactRowMapper;
import com.uow.Model.ScoutManage;
import com.uow.Model.ScoutManageRowMapper;
import com.uow.Model.User;
import com.uow.Model.UserInfo;
import com.uow.Model.UserInfoRowMapper;

@Repository
public class ScouterDAO {

	@Autowired
	private JdbcTemplate db;

	public UserInfo getScouterInfo(String username) {
		String sql = "Select u.username, roleID, firstName, lastName, HKID, DOB, gender, address, phoneNum, email, region, district, scoutGroup, DOI from User u left join PersonalInfo p on u.username = p.username Where u.username = ? and disable = 0 and roleID = 2";
		RowMapper<UserInfo> rowMapper = new UserInfoRowMapper();
		try {
			return this.db.queryForObject(sql, rowMapper, username);
		} catch (EmptyResultDataAccessException e) {
			return new UserInfo();
		}
	}

	public List<UserInfo> getAllUser() {
		String sql = "Select u.username, roleID, firstName, lastName, HKID, DOB, gender, address, phoneNum, email, region, district, scoutGroup, DOI from User u left join PersonalInfo p on u.username = p.username Where disable = 0 and roleID = 1";
		RowMapper<UserInfo> rowMapper = new UserInfoRowMapper();
		return this.db.query(sql, rowMapper);
	}

	public UserInfo getScoutInfo(String username) {
		String sql = "Select u.username, roleID, firstName, lastName, HKID, DOB, gender, address, phoneNum, email, region, district, scoutGroup, DOI from User u left join PersonalInfo p on u.username = p.username Where u.username = ? and disable = 0 and roleID = 1";
		RowMapper<UserInfo> rowMapper = new UserInfoRowMapper();
		try {
			return this.db.queryForObject(sql, rowMapper, username);
		} catch (EmptyResultDataAccessException e) {
			return new UserInfo();
		}
	}

	public List<ScoutManage> getScoutManageList() {
		String sql = "Select u.username, p.firstName, p.lastName, p.gender, p.email, ec.emerTel from User u left join PersonalInfo p on u.username = p.username left join UserEmer um on u.username = um.username left join EmerContact ec on um.emerContactID = ec.emerContactID Where disable = 0 and roleID = 1 group by username";
		RowMapper<ScoutManage> rowMapper = new ScoutManageRowMapper();
		return this.db.query(sql, rowMapper);
	}

	public List<EmerContact> getEmerContact(String username) {
		String sql = "Select u.username, emerName, emerAddress, emerRelation, emerTel from User u left join UserEmer um on u.username = um.username left join EmerContact ec on um.emerContactID = ec.emerContactID Where u.username = ? and disable = 0";
		RowMapper<EmerContact> rowMapper = new EmerContactRowMapper();
		return this.db.query(sql, rowMapper, username);
	}

	public boolean createUserProcess(User user) {
		boolean exists = false;
		String sql = "SELECT count(*) FROM User where username = ?";
		int count = db.queryForObject(sql, new Object[] { user.getUsername() }, Integer.class);
		exists = count > 0;
		if (!exists) {
			sql = "INSERT INTO User(username, password, roleID) " + "Values (?,?,?)";
			db.update(sql, user.getUsername(), user.getPassword(), user.getRoleID());
		}
		return exists;
	}

	public void editProfileProcess(String username, String email, int phoneNum) {
		String sql = "Update PersonalInfo SET email = ?, phoneNum = ? where username = ?";
		db.update(sql, email, phoneNum, username);
	}

	public void editScoutProfileProcess(String username, UserInfo userInfo) {
		String sql = "Select count(*) From PersonalInfo where username = ?";
		int count = db.queryForObject(sql, new Object[] { username }, Integer.class);
		System.out.println("count: " + count);
		if (count > 0) {
			sql = "Update PersonalInfo set firstName = ?, lastName = ?, HKID = ?, DOB = ?, gender = ?, address = ?, phoneNum = ?, email = ?, region = ?, district = ?, scoutGroup = ?, DOI = ? where username = ?";
			db.update(sql, userInfo.getFirstName(), userInfo.getLastName(), userInfo.getHKID(), userInfo.getDOB(),
					userInfo.getGender(), userInfo.getAddress(), userInfo.getPhoneNum(), userInfo.getEmail(),
					userInfo.getRegion(), userInfo.getDistrict(), userInfo.getScoutGroup(), userInfo.getDOI(),
					username);
		} else {
			sql = "INSERT INTO PersonalInfo(username, firstName, lastName, HKID, DOB, gender, address, phoneNum, email, region, district, scoutGroup, DOI)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			db.update(sql, username, userInfo.getFirstName(), userInfo.getLastName(), userInfo.getHKID(),
					userInfo.getDOB(), userInfo.getGender(), userInfo.getAddress(), userInfo.getPhoneNum(),
					userInfo.getEmail(), userInfo.getRegion(), userInfo.getDistrict(), userInfo.getScoutGroup(),
					userInfo.getDOI());
		}
	}

	public void editCompletedDate(String username, int questID, String finishDate) {

		String sql = "Select count(*) From CompletedQuest where username = ? and questID = ?";
		int count = db.queryForObject(sql, new Object[] { username, questID }, Integer.class);
		if (count > 0) {
			sql = "Update CompletedQuest set finishDate = ? where username = ? and questID = ?";
			db.update(sql, finishDate, username, questID);
		} else {
			sql = "Insert into CompletedQuest(finishDate, username, questID) " + "VALUES(?,?,?)";
			db.update(sql, finishDate, username, questID);
		}

	}

	public boolean checkSSA(String username) {
		boolean open = false;
		String sql = "Select count(*) From CompletedQuest where username = ? and questID = 10000";
		int count = db.queryForObject(sql, new Object[] { username }, Integer.class);
		if (count > 0) {
			open = true;
		}
		return open;
	}

	public boolean checkSAA(String username) {
		boolean open = false;
		String sql = "Select count(*) From CompletedQuest where username = ? and questID = 20000";
		int count = db.queryForObject(sql, new Object[] { username }, Integer.class);
		if (count > 0) {
			open = true;
		}
		return open;
	}

	public boolean checkCSA(String username) {
		boolean open = false;
		String sql = "Select count(*) From CompletedQuest where username = ? and questID = 30000";
		int count = db.queryForObject(sql, new Object[] { username }, Integer.class);
		if (count > 0) {
			open = true;
		}
		return open;
	}
	
	public void checkTask(String username, int questID) {
		String date;
		String subTaskNumSQL = "Select subTaskNum From Task where questID = ?";
		int subTaskNum = db.queryForObject(subTaskNumSQL, Integer.class, questID);
		int taskEnd = questID + 99;
		String countSQL = "Select count(*) From CompletedQuest where questID >= ? and questID < ? and mod(questID, 10) = 0 and username = ?";
		int count = db.queryForObject(countSQL, Integer.class, questID, taskEnd, username);
		if(count >= subTaskNum) {
			String latestDateSQL = "Select FinishDate From CompletedQuest where questID >= ? and questID < ? and username = ? ORDER BY FinishDate DESC limit 1";
			date = db.queryForObject(latestDateSQL, String.class, questID, taskEnd, username);
			editCompletedDate(username, questID, date);
			questID = questID - questID%1000;
			checkCategory(username, questID);
		}
	}
	
	public void checkCategory(String username, int questID) {
		String date;
		String TaskNumSQL = "Select taskNum From Category where questID = ?";
		int subTaskNum = db.queryForObject(TaskNumSQL, Integer.class, questID);
		int taskEnd = questID + 999;
		String countSQL = "Select count(*) From CompletedQuest where questID >= ? and questID < ? and mod(questID, 100) = 0 and username = ?";
		int count = db.queryForObject(countSQL, Integer.class, questID, taskEnd, username);
		if(count >= subTaskNum) {
			String latestDateSQL = "Select FinishDate From CompletedQuest where questID >= ? and questID < ? and username = ? ORDER BY FinishDate DESC limit 1";
			date = db.queryForObject(latestDateSQL, String.class, questID, taskEnd, username);
			editCompletedDate(username, questID, date);
			questID = questID - questID%10000;
			checkAward(username, questID);
		}
	}
	
	public void checkAward(String username, int questID) {
		String date;
		String TaskNumSQL = "Select categoryNum From Award where questID = ?";
		int subTaskNum = db.queryForObject(TaskNumSQL, Integer.class, questID);
		int taskEnd = questID + 9999;
		String countSQL = "Select count(*) From CompletedQuest where questID >= ? and questID < ? and mod(questID, 1000) = 0 and username = ?";
		int count = db.queryForObject(countSQL, Integer.class, questID, taskEnd, username);
		if(count >= subTaskNum) {
			String latestDateSQL = "Select FinishDate From CompletedQuest where questID >= ? and questID < ? and username = ? ORDER BY FinishDate DESC limit 1";
			date = db.queryForObject(latestDateSQL, String.class, questID, taskEnd, username);
			editCompletedDate(username, questID, date);
		}
	}
}
