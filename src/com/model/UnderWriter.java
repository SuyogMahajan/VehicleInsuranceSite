package com.model;

import java.sql.Date;

public class UnderWriter {
	
	private static int id_cnt = 1;
	
	private int id;
	private String username;
	private String password;
	private Date dateOfBirth;
	private Date dateOfJoining;
	
	
	public UnderWriter(String username, Date dateOfBirth, Date dateOfJoining) {
		this(id_cnt, username,  (username.toUpperCase()+"@123"), dateOfBirth, dateOfJoining);
		id_cnt++;
	}
	 
	public UnderWriter(int id, String username, String password, Date dateOfBirth, Date dateOfJoining) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
	}
	 
	@Override
	public String toString() {
		return "UnderWriter [id=" + id + ", username=" + username + ", password=" + password + ", dateOfBirth="
				+ dateOfBirth + ", dateOfJoining=" + dateOfJoining + "]\n";
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
}
