package com.server.socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TetrisServer {
	public void go() throws IOException {
		try {Class.forName("org.mariadb.jdbc.Driver");} catch (ClassNotFoundException e1) {e1.printStackTrace();}
		
		ServerSocket ss = null;
		Socket s = null;
		Connection conn = null;

		String url = "jdbc:mysql://database.cld8fsdm7gmh.us-east-2.rds.amazonaws.com:3306/shineTetris?useSSL=false";
		String user = "admin";
		String pass = "h201303037";
		// DataBase connect
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("** DB connect **");
		} catch (SQLException e) {e.printStackTrace();}

		// Socet Connect
		try {
			ss = new ServerSocket(54321);
			System.out.println("**server start**");
			// Server Thread
			while (true) {
				// Create each Clients
				s = ss.accept(); 
				ServerThread st = new ServerThread(s, conn);
				st.start();
				System.out.println(s.getInetAddress() + " connect!!");
			}
		} finally {
			try {
				if (conn != null) conn.close();
				if (s != null)  s.close();
				if (ss != null) ss.close();
			} catch (SQLException e) {e.printStackTrace();}
			System.out.println("**Close Server**");
		}
	}

	public static void main(String[] args) {
		TetrisServer ts = new TetrisServer();

		try {ts.go();} catch (IOException e) {e.printStackTrace();}

	}
}
