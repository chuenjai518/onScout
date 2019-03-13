package com.uow.DAO;

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
		try{
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
	
	public List<EmerContact> getEmerContact(String username){
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
}
