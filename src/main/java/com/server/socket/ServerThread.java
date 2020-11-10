package com.server.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServerThread extends Thread {
	//socket
	private Socket socket;
	private BufferedReader br = null; 
	private BufferedReader br2 = null; 
	private String userIP;
	
	//DataBase
	private Connection conn = null;
	private Statement stmt =null;
	private PreparedStatement ptmt = null;
	private ResultSet rs = null;
	
	//Rank result
	ArrayList<Object> list = null;
	private ObjectOutputStream oos = null;
	
	//sql문 고정
	private final String rankSQL = "select T.* from (select * from MEMBER order by 3 desc)T where rownum<=10 and score<>0";
	private final String insertSQL = "insert into MEMBER(id,score) values(?,?)";

	private String id;
	private String score;
	
	//Client Connect
	ServerThread(Socket s, Connection conn) {
		this.socket = s;
		this.userIP = s.getInetAddress().toString();
		this.conn = conn;
		try {
			this.stmt = conn.createStatement();
			this.ptmt = conn.prepareStatement(insertSQL);
		} catch (SQLException e) {e.printStackTrace();}
	}

	//Connect Check
	public void run() {
		try {
			service();
		} catch (IOException e) {
			System.out.println("**" + userIP + "bye");
		} finally {
			closeAll();
		}
	}

	private void service() throws IOException {
		String str = null;
		while (true) {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			str=br.readLine();
			if (str == null) {
				System.out.println(userIP + "bye");
				break;
			}
			
			switch (str) {
			// 1 : 10위 조회rank
			case "1":
				//TOP10 
				oos = new ObjectOutputStream(socket.getOutputStream());
				list = new ArrayList<>();
				try {rs = stmt.executeQuery(rankSQL);} 
					catch (SQLException e) {e.printStackTrace();}
				
				//Out
				try {
					while(rs.next()){
						list.add(new RankSerialize(rs.getString("writedate"), rs.getString("id"), rs.getString("score")));
					}
					oos.writeObject(list);
				} catch (SQLException e) {e.printStackTrace();}
				System.out.println(userIP + " view Rannking");
				break;
				
			// 2 : Rank insert
			case "2":
				br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				try {
					id = br2.readLine();
					ptmt.setString(1, id);
					score = br2.readLine();			
					System.out.println("gg: " +score);
					ptmt.setInt(2, Integer.parseInt(score));
					if(id!=null && score !=null) {
						ptmt.executeUpdate();
					}}
				catch (SQLException e) {}
				System.out.println(userIP+"Rank Save");
			default:
				break;
			}
		}
	}

	public void closeAll(){
		try {
			if(br!=null)br.close();
			if(br2!=null) br2.close();
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(ptmt!=null)ptmt.close();
			if(oos!=null)oos.close();
			if(socket!=null)socket.close();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}