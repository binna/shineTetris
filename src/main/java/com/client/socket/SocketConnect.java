package com.client.socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SocketConnect {
	Socket s = null;
	PrintWriter pw = null;
	ObjectInputStream ois = null;

	//Top10 Rankking
	ArrayList<Object> list = null;
	
	private static SocketConnect instance = new SocketConnect();
	
	public SocketConnect() {
		try {
			//서버ip 서버에 올릴떄 변경필요
			s = new Socket("127.0.0.1", 54321);
			System.out.println("Server Connect OK!!");
			
			pw=new PrintWriter(s.getOutputStream(), true);

		} catch (Exception e) {}
	}
	
	public static SocketConnect getInstance() {
		return instance;
	}
	
	
	public ArrayList<Object> RankSearch(){
		pw.println("1");
		try {
			ois = new ObjectInputStream(s.getInputStream());
			list= (ArrayList<Object>)ois.readObject();	
		} catch (Exception e) {}
		return list; 
	}
	
	public void InsertRank(int sc){
		pw.println("2");
		String ID="";
		
		while(true) {
			try {
				ID = (String)JOptionPane.showInputDialog("Input ID (length: 1~4)"); //ID 입력				
				if(ID.length()>1&&ID.length()<=4) { 
					String score = String.valueOf(sc);
//					pw.println(ID);
					pw.println("test"); //임시테스트용으로 박아놓음
					pw.println(score);
					JOptionPane.showMessageDialog(null,"등록 완료!");
					break;					
				} 
			}catch(NullPointerException e) { break; }
		}
	}
	
	
	public void closeAll(){
			try {
				if (ois != null) ois.close();
				if (pw != null) pw.close();
				if (s != null) s.close();
			} catch (IOException e) {}
		System.out.println("Server Connect Close..");
	}
}
