package com.server.socket;
import java.io.Serializable;
import java.sql.Date;

//Top10 Rankking Serializable
public class RankSerialize implements Serializable{
	private static final long serialVersionUID = 1L;
	private String date;
	private String id;
	private String score;
	
	public RankSerialize(String date,String id,String score) {
		this.date = date;
		this.id = id;
		this.score = score;
	}
	
	public String getDate(){
		return date;
	}
	public String getId(){
		return id;
	}
	public String getScore(){
		return score;
	}
	public String toString(){
		String str = date+"   "+id+"   "+score;
		return str;
	}
}
