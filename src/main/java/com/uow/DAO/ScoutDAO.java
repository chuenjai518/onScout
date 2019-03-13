package com.uow.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.UserInfo;
import com.uow.Model.UserInfoRowMapper;
import com.uow.Model.UserRowMapper;
import com.uow.Model.ScoutInfo;
import com.uow.Model.ScoutInfoRowMapper;
import com.uow.Model.User;


@Repository
public class ScoutDAO {
	@Autowired
	private JdbcTemplate db;
	
	public List<ScoutInfo> getAllScout() {
		String sql = "Select p.username, p.firstName, p.lastName, p.HKID, p.DOB, p.gender, p.address, p.phoneNum, p.email FROM PersonalInfo p, User u WHERE u.roleID = 1 AND u.userName = p.userName;";
		try {
			RowMapper<ScoutInfo> rowMapper = new ScoutInfoRowMapper();
			return this.db.query(sql, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
