package com.client.socket;
import java.io.Serializable;
import java.sql.Date;

//Top10 Rankking Serializable
public class RankSerialize implements Serializable{
	private static final long serialVersionUID = 1L;
	private String date;
	private String id;
	private String score;
	
	//public RankSerialize(String date,String id,String score) {
	public RankSerialize(String id,String score,String date) {
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
//		String str = id+"   "+score;
		return str;
	}
}
