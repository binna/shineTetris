package com.client.main;

import com.client.socket.SocketConnect;
import com.client.common.DropBlock;
import com.client.common.GameFrame;

public class GameMain{
	static DropBlock t2 = null;
	static Thread t1 = null;
	
	public static void gameStart(String flag) {
//	public static void main(String[] agrs) {
		// thread
		
		System.out.println("gameStart  flag >>"+flag);
		if("S".equals(flag)) {
			t1 = new Thread(new GameFrame());
			t2 = new DropBlock();
			t1.start();
			t2.start();
		}else if("E".equals(flag)) {
			System.out.println("gameStart  flag >>"+flag);
			t2.interrupt();
			t1.interrupt();
		}
		
	}

}
