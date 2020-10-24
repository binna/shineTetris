package com.client.main;

import com.client.socket.SocketConnect;
import com.client.common.DropBlock;
import com.client.common.GameFrame;

public class Main{
	
	
	public static void main(String[] args) {
		// thread
		DropBlock t2 = new DropBlock();
		Thread t1 = new Thread(new GameFrame());
		t1.start();
		t2.start();
	}

}
