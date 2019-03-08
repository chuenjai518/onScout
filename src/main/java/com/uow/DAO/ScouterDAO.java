package com.uow.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.UserInfo;
import com.uow.Model.UserInfoRowMapper;


@Repository
public class ScouterDAO {
	

	@Autowired
	private JdbcTemplate db;

	public List<UserInfo> getAllUser() {
		String sql = "Select u.username, roleID, firstName, lastName, HKID, DOB, gender, address, phoneNum, email, region from User u left join PersonalInfo p on u.username = p.username Where disable = 0";
		RowMapper<UserInfo> rowMapper = new UserInfoRowMapper();
		return this.db.query(sql, rowMapper);
	}
}
