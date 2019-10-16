package vn.edu.vinaenter.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.model.bean.Contact;

@Repository
public class ContactDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_ALL = "SELECT * FROM contacts ORDER BY id DESC";
	private static final String FIND_ITEMS_PAGINATION = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?, ?";
	private static final String FIND_SEARCH_ITEMS_PAGINATION = "SELECT * FROM contacts WHERE fullname LIKE ? ORDER BY id DESC LIMIT ?, ?";
	private static final String INSERT_ITEM = "INSERT INTO contacts(fullname, email, subject, content) VALUES(?, ?, ?, ?)";
	private static final String DELETE_ITEM = "DELETE FROM contacts WHERE id = ?";
	private static final String FIND_BY_ID = "SELECT * FROM contacts WHERE id = ?";
	private static final String UPDATE_BY_ID = "UPDATE contacts SET fullname = ?, email = ?, subject = ?, content = ? WHERE id = ?";
	private static final String COUNT_ITEMS = "SELECT COUNT(*) FROM contacts";
	private static final String COUNT_SEARCH_ITEMS = "SELECT COUNT(*) FROM contacts WHERE fullname LIKE ?";



	
	private BeanPropertyRowMapper<Contact> getBeanPropertyRowMapper() {
		return new BeanPropertyRowMapper<Contact>(Contact.class);
	}
	
	public List<Contact> getItems() {
		return jdbcTemplate.query(FIND_ALL, getBeanPropertyRowMapper());
	}
	
	
	public List<Contact> getItemsPagination(int offset) {
		return jdbcTemplate.query(FIND_ITEMS_PAGINATION, new Object[] { offset, Defines.ROW_COUNT }, getBeanPropertyRowMapper());
	}

	public List<Contact> getSearchItemsPagination(String searchString, int offset) {
		return jdbcTemplate.query(FIND_SEARCH_ITEMS_PAGINATION, new Object[] { "%" + searchString + "%", offset, Defines.ROW_COUNT }, getBeanPropertyRowMapper());
	}
	
	public int addItem(Contact objContact) {
		return jdbcTemplate.update(INSERT_ITEM, new Object[] { objContact.getFullname(), objContact.getEmail(),
				objContact.getSubject(), objContact.getContent() });
	}

	public Contact getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { id },getBeanPropertyRowMapper());
	}

	public int editItem(Contact objContact) {
		return jdbcTemplate.update(UPDATE_BY_ID, new Object[] { objContact.getFullname(), objContact.getEmail(),
				objContact.getSubject(), objContact.getContent(), objContact.getId() });
	}

	public int deleteItem(int id) {
		return jdbcTemplate.update(DELETE_ITEM, new Object[] { id });
	}

	public int countItems() {
		return jdbcTemplate.queryForObject(COUNT_ITEMS, Integer.class);
	}

	public int countSearchItems(String searchString) {
		return jdbcTemplate.queryForObject(COUNT_SEARCH_ITEMS, new Object[] { "%" + searchString + "%" }, Integer.class);
	}

	

}
