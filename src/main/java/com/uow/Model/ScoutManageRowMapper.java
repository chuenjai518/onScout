package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class ScoutManageRowMapper implements RowMapper<ScoutManage> {

	@Override
	public ScoutManage mapRow(ResultSet row, int rowNum) throws SQLException {
		ScoutManage scout = new ScoutManage();
		scout.setUsername(row.getString("username"));
		scout.setFirstName(row.getString("firstName"));
		scout.setLastName(row.getString("lastName"));
		scout.setGender(row.getString("gender"));
		scout.setEmail(row.getString("email"));
		scout.setEmerTel(row.getInt("emerTel"));
		
		return scout;
	}

}
