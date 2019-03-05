package com.uow.DAO;

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

	public List<User> getAllUser() {
		String sql = "Select username, roleID from User Where disable = 0";
		RowMapper<User> rowMapper = new UserRowMapper();
		return this.db.query(sql, rowMapper);
	}

	public User loginProcess(User user) {
		String sql = "Select username, roleID from User Where username = ? and password = ? and disable = 0";
		RowMapper<User> rowMapper = new UserRowMapper();
		try {
			User temp = db.queryForObject(sql, rowMapper, user.getUsername(), user.getPassword());
			return temp;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
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

	public void forgetPassword(String username) {
		String sql = "Update User set password = username Where username = ?";
		db.update(sql, username);
	}
	
	public void disableUser(String username) {
		String sql = "Update User set disable = 1 Where username = ?";
		db.update(sql, username);
	}

}
