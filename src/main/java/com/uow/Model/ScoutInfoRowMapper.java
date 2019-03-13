package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class ScoutInfoRowMapper implements RowMapper<ScoutInfo> {

	@Override
	public ScoutInfo mapRow(ResultSet row, int rowNum) throws SQLException {
		ScoutInfo scout = new ScoutInfo();
		scout.setUsername(row.getString("username"));
		scout.setFirstName(row.getString("firstName"));
		scout.setLastName(row.getString("lastName"));
		scout.setGender(row.getString("gender"));
		scout.setEmail(row.getString("email"));
		scout.setDOB(row.getString("DOB"));
		scout.setHKID(row.getString("HKID"));
		scout.setAddress(row.getString("address"));
		scout.setPhoneNum(row.getString("phoneNum"));
		
		return scout;
	}

}