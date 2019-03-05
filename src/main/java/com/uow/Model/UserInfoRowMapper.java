package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class UserInfoRowMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet row, int rowNum) throws SQLException {
		UserInfo user = new UserInfo();
		user.setRoleID(row.getInt("roleID"));
		user.setUsername(row.getString("username"));
		user.setFirstName(row.getString("firstName"));
		user.setLastName(row.getString("lastName"));
		user.setHKID(row.getString("HKID"));
		user.setDOB(row.getDate("DOB"));
		user.setGender(row.getString("gender"));
		user.setAddress(row.getString("address"));
		user.setPhoneNum(row.getInt("phoneNum"));
		user.setEmail(row.getString("email"));
		user.setRegion(row.getString("region"));
		
		return user;
	}

}
