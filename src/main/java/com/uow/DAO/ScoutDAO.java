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
import com.uow.Model.ScoutManage;
import com.uow.Model.ScoutManageRowMapper;
import com.uow.Model.User;


@Repository
public class ScoutDAO {
	@Autowired
	private JdbcTemplate db;
	
	public UserInfo getScout(String username) {
		
		String sql = "Select u.username, roleID, firstName, lastName, HKID, DOB, gender, address, phoneNum, email, region, district, scoutGroup, DOI from User u left join PersonalInfo p on u.username = p.username Where u.username = ? and disable = 0 and roleID = 1";
		RowMapper<UserInfo> rowMapper = new UserInfoRowMapper();
		try{
			return this.db.queryForObject(sql, rowMapper, username);
		} catch (EmptyResultDataAccessException e) {
			return new UserInfo();
		}
	}
}
