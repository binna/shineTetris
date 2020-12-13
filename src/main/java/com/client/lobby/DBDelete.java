package com.client.lobby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class DBDelete {
	MsgeBox msgbox = new MsgeBox();

	String id = null;

	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://database.cld8fsdm7gmh.us-east-2.rds.amazonaws.com:3306/shineTetris?useSSL=false"; // 오라클 포트번호1521/@이후에는 IP주소
	String sql = null;
	String sql2 = null;
	Properties info = null;
	Connection cnn = null;

	// id를 받아와서 그것의 정보로 pw/name/birth 삭제
	public int InfoDel(String id) {
		int result = 0;
		this.id = id;
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // 알아서 들어간다..conn로
//			info = new Properties();
//			info.setProperty("username", "admin");
//			info.setProperty("password", "h201303037");
//			cnn = DriverManager.getConnection(url, info); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
			cnn = DriverManager.getConnection(
					url,
					"admin",
					"h201303037"); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
			stmt = cnn.createStatement();

			sql = "delete from tcm_mbmber where user_id='" + id + "'";
			stmt.executeUpdate(sql);

			sql = "select * from tcm_mbmber where user_id='" + id + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next() == true) { // 다음값의
				result = 0; // 실패
			} else {
				result = 1; // 성공
			}
		} catch (Exception ee) {
			System.out.println("문제있음");
			ee.printStackTrace();
		}

		return result;
	}

}