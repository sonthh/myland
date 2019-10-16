package vn.edu.vinaenter.model.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
	private int id;
	@NotBlank
	@Size(min = 5, max = 32)
	private String username;
	@NotBlank
	@Size(min = 5, max = 32)
	private String fullname;
	private String password;
	private int enable;
	private Role role;
	
	private String email;

	public User(int id, String username, String fullname, String password, int enable, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.enable = enable;
		this.role = role;
	}
	
	public User(int id, String username, String fullname, String password, int enable, Role role, String email) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.enable = enable;
		this.role = role;
		this.email = email;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fullname=" + fullname + ", password=" + password
				+ ", enable=" + enable + ", role=" + role + "]";
	}

}
