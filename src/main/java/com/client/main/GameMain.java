package com.client.main;

import com.client.socket.SocketConnect;
import com.client.common.Block;
import com.client.common.Data;
import com.client.common.DropBlock;
import com.client.common.GameFrame;

public class GameMain{
	DropBlock t2 = new DropBlock();
	//static Thread t1 = null;

	private GameMain(){}
	public static class GameSingleton{
		private static final GameMain instance = new GameMain();
	}

	public static GameMain getInstance(){
		return GameSingleton.instance;
	}

	public void gameStart() {
		System.out.println("gameStart!!! ");
		GameFrame gameFrame = new GameFrame();
		gameFrame.run();
		t2.start();
	}
	
	public void gameExit() {
		System.out.println("gameEnd!!! ");		
		Block.getInstance().distroy();
		Data.getInstance().distroy();
		t2.interrupt();
	}
	
//	public static void main(String[] agrs) {
//		// thread
//		GameFrame gameFrame = new GameFrame();
//		Thread t1 = new Thread(gameFrame);
//		DropBlock t2 = new DropBlock();
//		t1.start();
//		t2.start();
//	}
		
}
