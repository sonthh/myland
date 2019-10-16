package vn.edu.vinaenter.model.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Category {

	private int id;
	@NotBlank
	private String name;

	public Category(int id, @NotEmpty String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
