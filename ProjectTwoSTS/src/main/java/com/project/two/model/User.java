package com.project.two.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="username", nullable=false, unique=true, length=64)
	private String uname;
	
	@JsonProperty(access=Access.WRITE_ONLY) // NEVER send user passwords to the client
	@Column(name="pwd", nullable=false, length=64)
	private String pwd;
	
	@Column(name="email", nullable=false, unique=true, length=64)
	private String email;
	
	@Column(name="pfp", unique=true, length=256)
	private String pfp;
	
	public User() {}
	
	public User(int id) {
		this.id = id;
	}

	public User(String uname, String pwd, String email, String pfp) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.email = email;
		this.pfp = pfp;
	}

	public User(int id, String uname, String pwd, String email, String pfp) {
		super();
		this.id = id;
		this.uname = uname;
		this.pwd = pwd;
		this.email = email;
		this.pfp = pfp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPfp() {
		return pfp;
	}

	public void setPfp(String pfp) {
		this.pfp = pfp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", pwd=" + pwd + ", email=" + email + ", pfp=" + pfp + "]";
	}
	
}
