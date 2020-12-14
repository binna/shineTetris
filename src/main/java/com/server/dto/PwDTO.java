package com.server.dto;

public class PwDTO {
	private String user_id;
	private String user_pw;
	private String userpw_now;
	
	public PwDTO() {}
	
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

	public String getUserpw_now() {
		return userpw_now;
	}
	public void setUserpw_now(String userpw_now) {
		this.userpw_now = userpw_now;
	}
} // end DTO