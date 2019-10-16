package vn.edu.vinaenter.model.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import com.sun.istack.internal.NotNull;

public class Slide {
	private int id;
	@NotBlank
	private String title;
	@NotBlank
	@URL
	private String link;
	private String picture;
	@NotNull
	private Integer sort;
	private int active;

	public Slide(int id, @NotEmpty String title, @NotEmpty String link, String picture, Integer sort, int active) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
		this.picture = picture;
		this.sort = sort;
		this.active = active;
	}

	public Slide() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Slide [id=" + id + ", title=" + title + ", link=" + link + ", picture=" + picture + ", sort=" + sort
				+ ", active=" + active + "]";
	}

}
