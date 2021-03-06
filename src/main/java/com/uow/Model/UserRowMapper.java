package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	// username, password, roleID
	@Override
	public User mapRow(ResultSet row, int rowNum) throws SQLException {
		User user = new User();
		user.setRoleID(row.getInt("roleID"));
		user.setUsername(row.getString("username"));

		return user;
		

	}

}
