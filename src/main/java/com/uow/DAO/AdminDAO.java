package com.uow.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.User;
import com.uow.Model.UserRowMapper;


@Repository
public class AdminDAO {

	@Autowired
	private JdbcTemplate db;
	
	public List<User> getAllUser(){
		String sql = "Select username, roleID from User";
		RowMapper<User> rowMapper = new UserRowMapper();
		return this.db.query(sql, rowMapper);
	}
	
	public User loginProcess(User user) {
		String sql = "Select username, roleID from User Where username = ? and password = ?";
		RowMapper<User> rowMapper = new UserRowMapper();
		try {
			User temp = db.queryForObject(sql, rowMapper, user.getUsername(), user.getPassword());
			System.out.println("Found User - " + user.getUsername());
			return temp;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
