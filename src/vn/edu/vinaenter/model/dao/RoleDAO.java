package vn.edu.vinaenter.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.model.bean.Role;

@Repository
public class RoleDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_ALL = "SELECT * FROM roles"; 
	
	private BeanPropertyRowMapper<Role> getBeanPropertyRowMapper() {
		return new BeanPropertyRowMapper<Role>(Role.class);
	}
	
	public List<Role> getItems() {
		return jdbcTemplate.query(FIND_ALL, getBeanPropertyRowMapper());
	}
	
}
