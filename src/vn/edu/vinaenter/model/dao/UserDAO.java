package vn.edu.vinaenter.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.model.bean.Role;
import vn.edu.vinaenter.model.bean.User;

@Repository
public class UserDAO {
	
	private static final String FIND_ITEMS_PAGINATION = "SELECT u.*, r.name AS rname FROM users AS u INNER JOIN roles AS r ON u.roleID = r.id ORDER BY u.id DESC LIMIT ?, ?";
	private static final String FIND_SEARCH_ITEMS_PAGINATION = "SELECT u.*, r.name AS rname FROM users AS u INNER JOIN roles AS r ON u.roleID = r.id WHERE u.username LIKE ? ORDER BY u.id DESC LIMIT ?, ?";
	private static final String FIND_ITEM_BY_ID = "SELECT u.*, r.name AS rname FROM users AS u INNER JOIN roles AS r ON u.roleID = r.id WHERE u.id = ?";
	private static final String FIND_ITEM_BY_EMAIL = "SELECT u.*, r.name AS rname FROM users AS u INNER JOIN roles AS r ON u.roleID = r.id WHERE u.email = ?";
	private static final String FIND_ITEM_BY_USERNAME = "SELECT u.*, r.name AS rname FROM users AS u INNER JOIN roles AS r ON u.roleID = r.id WHERE u.username = ?";
	private static final String FIND_USER_BY_USERNAME_PASSWORD = "SELECT u.*, r.name AS rname FROM users AS u INNER JOIN roles AS r ON u.roleID = r.id WHERE u.username = ? AND u.password = ?";
	
	private static final String FIND_ITEM_BY_USERNAME_ENABLE = "SELECT u.*, r.name AS rname FROM users AS u INNER JOIN roles AS r ON u.roleID = r.id WHERE u.username = ? AND u.enable = 1";
	private static final String COUNT_ITEMS = "SELECT COUNT(*) FROM users";
	private static final String COUNT_SEARCH_ITEMS = "SELECT COUNT(*) FROM users WHERE username LIKE ?";
	private static final String INSERT_ITEM = "INSERT INTO users(username, fullname, password, enable, roleId) VALUES(?, ?, ?, ?, ?);";
	private static final String INSERT_ITEM_USER_REGISTER = "INSERT INTO users(username, fullname, password, enable, roleId, email) VALUES(?, ?, ?, ?, ?, ?);";
	private static final String EDIT_ITEM = "UPDATE users SET fullname = ?, password = ?, roleId = ? WHERE id = ?";
	private static final String HAS_USER_BY_USERNAME = "SELECT COUNT(*) FROM users WHERE username = ?";
	private static final String DEL_ITEM_BY_ID = "DELETE FROM users WHERE id = ?";
	private static final String ENABLE_USER = "UPDATE users SET enable = ? WHERE id = ?";
	private static final String COUNT_EMAIL = "SELECT count(*) AS countEmail FROM users WHERE email = ?";
	private static final String COUNT_USER_BY_USERNAME_PASSWORD = "SELECT COUNT(*) countUserByUsernamePassword FROM users WHERE username = ? AND password = ?";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> getRowMapper() {
		return new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"),
						rs.getString("password"), rs.getInt("enable"), new Role(rs.getInt("roleId"), rs.getString("rname")));
			}
		};
	}

	public int countItems() {
		return jdbcTemplate.queryForObject(COUNT_ITEMS, Integer.class);
	}
	
	public int countSearchItems(String searchString) {
		return jdbcTemplate.queryForObject(COUNT_SEARCH_ITEMS, new Object[] { "%" + searchString + "%" }, Integer.class);
	}

	public User getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_ITEM_BY_ID, new Object[] { id }, getRowMapper());
	}
	
	public User getItemByUsername(String username) {
		return jdbcTemplate.queryForObject(FIND_ITEM_BY_USERNAME, new Object[] { username }, getRowMapper());
	}
	
	public User getItemByEmail(String email) {
		return jdbcTemplate.queryForObject(FIND_ITEM_BY_EMAIL, new Object[] { email }, getRowMapper());
	}
	
	public User getItemByUsernameEnable(String username) {
		return jdbcTemplate.queryForObject(FIND_ITEM_BY_USERNAME_ENABLE, new Object[] { username }, getRowMapper());
	}
	
	public List<User> getItemsPagination(int offset) {
		return jdbcTemplate.query(FIND_ITEMS_PAGINATION, new Object[] { offset, Defines.ROW_COUNT }, getRowMapper());
	}
	
	public List<User> getSearchItemsPagination(String searchString, int offset) {
		return jdbcTemplate.query(FIND_SEARCH_ITEMS_PAGINATION, new Object[] { "%" + searchString + "%", offset, Defines.ROW_COUNT }, getRowMapper());
	}

	public int addItem(User objUser) {
		return jdbcTemplate.update(INSERT_ITEM, new Object[] { objUser.getUsername(), objUser.getFullname(),
				objUser.getPassword(), objUser.getEnable(), objUser.getRole().getId() });
	}
	
	public int addItemUserRegister(User objUser) {
		return jdbcTemplate.update(INSERT_ITEM_USER_REGISTER, new Object[] { objUser.getUsername(), objUser.getFullname(),
				objUser.getPassword(), objUser.getEnable(), objUser.getRole().getId(), objUser.getEmail() });
	}

	public int editItem(User objUser) {
		return jdbcTemplate.update(EDIT_ITEM, new Object[] { objUser.getFullname(),
				objUser.getPassword(), objUser.getRole().getId(), objUser.getId() });
	}

	public boolean hasUser(String username) {
		return jdbcTemplate.queryForObject(HAS_USER_BY_USERNAME, new Object[] { username }, Boolean.class);
	}

	public int delItem(int id) {
		return jdbcTemplate.update(DEL_ITEM_BY_ID, new Object[] { id });
	}

	public int enableUser(int id, int enable) {
		return jdbcTemplate.update(ENABLE_USER, new Object[] { enable, id });
	}
	
	public int countEmail(String email) {
		return jdbcTemplate.queryForObject(COUNT_EMAIL, new Object[] { email }, Integer.class);
	}
	
	public int countUserByUsernamePassword(String username, String password) {
		return jdbcTemplate.queryForObject(COUNT_USER_BY_USERNAME_PASSWORD, new Object[] { username, password }, Integer.class);
	}
	public User getItemByUsernamePassword(String username, String password) {
		return jdbcTemplate.queryForObject(FIND_USER_BY_USERNAME_PASSWORD, new Object[] { username, password }, getRowMapper());
	}

}
