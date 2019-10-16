package vn.edu.vinaenter.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.model.bean.Category;
import vn.edu.vinaenter.model.bean.Land;

@Repository
public class LandDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_ALL = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id ORDER BY l.id DESC";
	private static final String FIND_ITEMS_PAGINATION = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id ORDER BY l.id DESC LIMIT ?, ?";
	private static final String FIND_ITEMS_PAGINATION_BY_CAT_ID = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id WHERE c.id = ? ORDER BY l.id DESC LIMIT ?, ?";
	private static final String FIND_SEARCH_ITEMS_PAGINATION = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id WHERE l.name LIKE ? ORDER BY l.id DESC LIMIT ?, ?";
	private static final String FIND_TOP = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id ORDER BY l.id DESC LIMIT ?";
	private static final String FIND_TOP_VIEWS = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id ORDER BY l.views DESC LIMIT ?";;
	private static final String SELECT_PICTURES_BY_CAT_ID = "SELECT picture AS pictures FROM lands WHERE categoryId = ?";
	private static final String INSERT_ITEM = "INSERT INTO lands(name, description, detail, categoryId, picture, area, address)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?);";
	private static final String DELETE_ITEM = "DELETE FROM lands WHERE id = ?";
	private static final String DELETE_ITEMS_BY_CAT_ID = "DELETE FROM lands WHERE categoryId = ?";
	private static final String FIND_BY_ID = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id WHERE l.id = ?";
	private static final String FIND_BY_CAT_ID = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id WHERE c.id = ?";
	private static final String FIND_RELATED_ITEMS_LIMIT = "SELECT l.*, c.name AS cname  FROM lands AS l INNER JOIN categories AS c ON l.categoryId = c.id WHERE c.id = ? AND l.id != ? ORDER BY RAND() LIMIT ?";
	private static final String UPDATE_BY_ID = "UPDATE lands SET name = ?, description = ?, detail = ?, categoryId = ?, picture = ?, area = ?, address = ? WHERE id = ?";
	private static final String COUNT_ITEMS = "SELECT COUNT(*) FROM lands";
	private static final String COUNT_SEARCH_ITEMS = "SELECT COUNT(*) FROM lands WHERE name LIKE ?";
	private static final String COUNT_ITEMS_BY_CAT_ID = "SELECT COUNT(*) FROM lands WHERE categoryId = ?";
	private static final String INCREASE_VIEW = "UPDATE lands SET views = views + 1 WHERE id = ?";


	
	/* row mapper Land width resultset: sql: lands INNER JOIN categories */
	private RowMapper<Land> getRowMapper() {
		return new RowMapper<Land>() {
			@Override
			public Land mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Land(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getString("detail"), rs.getTimestamp("dateCreate"),
						new Category(rs.getInt("categoryId"), rs.getString("cname")), rs.getString("picture"),
						rs.getDouble("area"), rs.getString("address"), rs.getInt("views"));
			}
		};
	}
	
	public List<Land> getItems() {
		return jdbcTemplate.query(FIND_ALL, getRowMapper());
	}
	
	public List<Land> getItemsPagination(int offset) {
		return jdbcTemplate.query(FIND_ITEMS_PAGINATION, new Object[] { offset, Defines.ROW_COUNT }, getRowMapper());
	}
	
	public List<Land> getItemsPaginationsByCategoryId(int offset, int categoryId) {
		return jdbcTemplate.query(FIND_ITEMS_PAGINATION_BY_CAT_ID, new Object[] { categoryId, offset, Defines.ROW_COUNT }, getRowMapper());
	}

	
	public List<Land> getSearchItemsPagination(String searchString, int offset) {
		return jdbcTemplate.query(FIND_SEARCH_ITEMS_PAGINATION, new Object[] { "%" + searchString + "%", offset, Defines.ROW_COUNT }, getRowMapper());
	}
	
	public List<Land> getTopItems(int size) {
		return jdbcTemplate.query(FIND_TOP, new Object[] { size }, getRowMapper());
	}
	
	public List<Land> getTopViewsItems(int size) {
		return jdbcTemplate.query(FIND_TOP_VIEWS, new Object[] { size }, getRowMapper());
	}
	
	public List<Land> getItemsByCategoryId(int categoryId) {
		return jdbcTemplate.query(FIND_BY_CAT_ID, new Object[] { categoryId }, getRowMapper());
	}
	
	/**
	 * @author Dell
	 * @param objLand
	 * @param size
	 * @return danh sách tin tức có cùng danh mục mà phải khác chính tin đó
	 */
	public List<Land> getRelatedItemsLimit(Land objLand, int size) {
		return jdbcTemplate.query(FIND_RELATED_ITEMS_LIMIT, new Object[] { objLand.getCategory().getId(), objLand.getId(), size }, getRowMapper());
	}
	
	public Land getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { id }, getRowMapper());
	}
	
	public int addItem(Land objLand) {
		return jdbcTemplate.update(INSERT_ITEM,
				new Object[] { objLand.getName(), objLand.getDescription(), objLand.getDetail(),
						objLand.getCategory().getId(), objLand.getPicture(), objLand.getArea(), objLand.getAddress() });
	}


	public int editItem(Land objLand) {
		return jdbcTemplate.update(UPDATE_BY_ID,
				new Object[] { objLand.getName(), objLand.getDescription(), objLand.getDetail(),
						objLand.getCategory().getId(), objLand.getPicture(), objLand.getArea(), objLand.getAddress(),
						objLand.getId() });
	}

	public int delItem(int id) {
		return jdbcTemplate.update(DELETE_ITEM, new Object[] { id });
	}
	
	
	/* get all pictures file name of all Land of Category(categoryId) */
	public List<String> getPicturesByCatId(int categoryId) {
		return jdbcTemplate.queryForList(SELECT_PICTURES_BY_CAT_ID, new Object[] { categoryId }, String.class);
	}
	
	public int delItemsByCategoryId(int categoryId) {
		return jdbcTemplate.update(DELETE_ITEMS_BY_CAT_ID, new Object[] { categoryId });
	}

	public int countItems() {
		return jdbcTemplate.queryForObject(COUNT_ITEMS, Integer.class);
	}
	
	public int countSearchItems(String searchString) {
		return jdbcTemplate.queryForObject(COUNT_SEARCH_ITEMS, new Object[] { "%" + searchString + "%" }, Integer.class);
	}
	
	public int countItemsByCategoryId(int categoryId) {
		return jdbcTemplate.queryForObject(COUNT_ITEMS_BY_CAT_ID, new Object[] { categoryId }, Integer.class);
	}

	public void increaseView(int id) {
		jdbcTemplate.update(INCREASE_VIEW, new Object[] { id });
	}

	

}
