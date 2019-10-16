package vn.edu.vinaenter.model.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.model.bean.Slide;

@Repository
public class SlideDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//private static final String FIND_ALL = "SELECT * FROM slides ORDER BY id DESC";
	private static final String FIND_ITEMS_PAGINATION = "SELECT * FROM slides ORDER BY id DESC LIMIT ?, ?";
	private static final String FIND_TOP_ORDER_ITEMS = "SELECT * FROM slides WHERE active = 1 ORDER BY sort DESC LIMIT ?";
	private static final String FIND_ITEM_BY_ID = "SELECT * FROM slides WHERE id = ?";
	private static final String FIND_SEARCH_ITEMS_PAGINATION = "SELECT * FROM slides WHERE title LIKE ? ORDER BY id DESC LIMIT ?, ?";
	private static final String COUNT_ITEMS = "SELECT COUNT(*) FROM slides";
	private static final String COUNT_SEARCH_ITEMS = "SELECT COUNT(*) FROM slides WHERE title LIKE ?";
	private static final String ENABLE_SLIDE = "UPDATE slides SET active = ? WHERE id = ?";
	private static final String INSERT_ITEM = "INSERT INTO slides(title, link, picture, sort)"
			+ " VALUES(?, ?, ?, ?);";
	private static final String EDIT_ITEM = "UPDATE slides SET title = ?, link = ?, picture = ?, sort = ? WHERE id = ?";
	private static final String DEL_ITEM = "DELETE FROM slides WHERE id = ?";
	
	private BeanPropertyRowMapper<Slide> getBeanPropertyRowMapper() {
		return new BeanPropertyRowMapper<Slide>(Slide.class);
	}

	public int countItems() {
		return jdbcTemplate.queryForObject(COUNT_ITEMS, Integer.class);
	}
	
	public int countSearchItems(String searchString) {
		return jdbcTemplate.queryForObject(COUNT_SEARCH_ITEMS, new Object[] { "%" + searchString + "%" }, Integer.class);
	}

	public List<Slide> getItemsPagination(int offset) {
		return jdbcTemplate.query(FIND_ITEMS_PAGINATION, new Object[] { offset, Defines.ROW_COUNT }, getBeanPropertyRowMapper());
	}

	public List<Slide> getSearchItemsPagination(String searchString, int offset) {
		return jdbcTemplate.query(FIND_SEARCH_ITEMS_PAGINATION, new Object[] { "%" + searchString + "%", offset, Defines.ROW_COUNT }, getBeanPropertyRowMapper());
	}

	public int enableSlide(int id, int enable) {
		return jdbcTemplate.update(ENABLE_SLIDE, new Object[] { enable, id });
	}

	public int addItem(@Valid Slide objSlide) {
		return jdbcTemplate.update(INSERT_ITEM, new Object[] { objSlide.getTitle(), objSlide.getLink(), objSlide.getPicture(), objSlide.getSort() });
	}

	public Slide getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_ITEM_BY_ID, new Object[] { id }, getBeanPropertyRowMapper());
	}
	
	public List<Slide> getTopOrderItems(int size) {
		return jdbcTemplate.query(FIND_TOP_ORDER_ITEMS, new Object[] { size }, getBeanPropertyRowMapper());
	}

	public int editItem(Slide objSlide) {
		return jdbcTemplate.update(EDIT_ITEM, 
				new Object[] { objSlide.getTitle(), objSlide.getLink(), objSlide.getPicture(), objSlide.getSort(), objSlide.getId() });
	}

	public int delItem(int id) {
		return jdbcTemplate.update(DEL_ITEM, new Object[] { id });
	}
	
}
