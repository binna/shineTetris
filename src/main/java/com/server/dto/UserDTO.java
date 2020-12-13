package com.server.dto;

public class UserDTO {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_email;
	private String user_zipcode;
	private String user_address1;
	private String user_address2; 
	
	
	public UserDTO() {}


	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_zipcode() {
		return user_zipcode;
	}
	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}

	public String getUser_address1() {
		return user_address1;
	}
	public void setUser_address1(String user_address1) {
		this.user_address1 = user_address1;
	}

	public String getUser_address2() {
		return user_address2;
	}
	public void setUser_address2(String user_address2) {
		this.user_address2 = user_address2;
	}
} // end DTO