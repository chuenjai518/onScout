package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class EmerContactRowMapper implements RowMapper<EmerContact> {

	@Override
	public EmerContact mapRow(ResultSet row, int rowNum) throws SQLException {
		EmerContact contact = new EmerContact();
		contact.setUsername(row.getString("username"));
		contact.setEmerName(row.getString("emerName"));
		contact.setEmerAddress(row.getString("emerAddress"));
		contact.setEmerRelation(row.getString("emerRelation"));
		contact.setEmerTel(row.getInt("emerTel"));
		
		return contact;
	}

}
