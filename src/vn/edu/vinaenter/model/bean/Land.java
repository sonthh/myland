package vn.edu.vinaenter.model.bean;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

public class Land {
	private int id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotBlank
	private String detail;
	private Timestamp dateCreate;
	private Category category;
	private String picture;
	@NotNull
	private Double area;
	@NotBlank
	private String address;
	private int views;

	

	public Land(int id, @NotEmpty String name, @NotEmpty String description, @NotEmpty String detail,
			Timestamp dateCreate, Category category, String picture, double area, String address, int views) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.detail = detail;
		this.dateCreate = dateCreate;
		this.category = category;
		this.picture = picture;
		this.area = area;
		this.address = address;
		this.views = views;
	}

	public Land() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getArea() {
		return area;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "Land [id=" + id + ", name=" + name + ", description=" + description + ", detail=" + detail
				+ ", dateCreate=" + dateCreate + ", category=" + category + ", picture=" + picture + ", area=" + area
				+ ", address=" + address + ", views=" + views + "]";
	}

	

}
