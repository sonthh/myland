package vn.edu.vinaenter.model.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Contact {
	private int id;
	@NotBlank
	private String fullname;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String subject;
	@NotBlank
	private String content;

	public Contact() {
		super();
	}

	public Contact(int id, String fullname, String email, String subject, String content) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.subject = subject;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", fullname=" + fullname + ", email=" + email + ", subject=" + subject
				+ ", content=" + content + "]";
	}

}
