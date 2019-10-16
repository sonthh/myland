package vn.edu.vinaenter.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.model.bean.Category;

@Repository
public class CategoryDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_ALL = "SELECT * FROM categories ORDER BY id DESC";
	private static final String FIND_ITEMS_PAGINATION = "SELECT * FROM categories ORDER BY id DESC LIMIT ?, ?";
	private static final String FIND_SEARCH_ITEMS_PAGINATION = "SELECT * FROM categories WHERE name LIKE ? ORDER BY id DESC LIMIT ?, ?";
	private static final String FIND_TOP = "SELECT * FROM categories ORDER BY id DESC LIMIT ?";
	private static final String INSERT_SQL = "INSERT INTO categories(name) VALUES(?)";
	private static final String DELETE_SQL = "DELETE FROM categories WHERE id = ?";
	private static final String FIND_BY_ID = "SELECT * FROM categories WHERE id = ?";
	private static final String UPDATE_BY_ID = "UPDATE categories SET name = ? WHERE id = ?";
	private static final String COUNT_ITEMS = "SELECT COUNT(*) FROM categories";
	private static final String COUNT_SEARCH_ITEMS = "SELECT COUNT(*) FROM categories WHERE name LIKE ?";

	
	private BeanPropertyRowMapper<Category> getBeanProperty() {
		return new BeanPropertyRowMapper<Category>(Category.class);
	}
	
	public List<Category> getItems() {
		return jdbcTemplate.query(FIND_ALL, getBeanProperty());
	}
	
	public List<Category> getItemsPagination(int offset) {
		return jdbcTemplate.query(FIND_ITEMS_PAGINATION, new Object[] { offset, Defines.ROW_COUNT }, getBeanProperty());
	}
	
	public List<Category> getItemsTop(int size) {
		return jdbcTemplate.query(FIND_TOP, new Object[] { size }, getBeanProperty());
	}

	public int addItem(Category objCat) {
		return jdbcTemplate.update(INSERT_SQL, new Object[] { objCat.getName() });
	}

	public int delItem(int id) {
		return jdbcTemplate.update(DELETE_SQL, new Object[] { id });
	}
	
	public Category getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { id }, getBeanProperty());
	}
	
	public int editItem(Category objCat) {
		return jdbcTemplate.update(UPDATE_BY_ID, new Object[] { objCat.getName(), objCat.getId() });
	}
	
	public int countItems() {
		return jdbcTemplate.queryForObject(COUNT_ITEMS, Integer.class);
	}

	public int countSearchItems(String searchString) {
		return jdbcTemplate.queryForObject(COUNT_SEARCH_ITEMS, new Object[] { "%" +  searchString + "%" }, Integer.class);
	}

	public List<Category> getSearchItemsPagination(String searchString, int offset) {
		return jdbcTemplate.query(FIND_SEARCH_ITEMS_PAGINATION, new Object[] { "%" +  searchString + "%", offset, Defines.ROW_COUNT }, getBeanProperty());
	}

	
}
