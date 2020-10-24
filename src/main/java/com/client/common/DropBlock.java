package com.client.common;

public class DropBlock extends Thread{
	
	private void startCount() {
		try{
			for(int i=0;i<20;i++){
				Data.startCountFontSize-=7;
				Thread.sleep(50);
				GameFrame.bgp.repaint();
			}
			Data.startCountFontSize=260;
			Data.startCount--;
			if(Data.startCount==0){
				Block.getInstance().gameStart();
				GameFrame.bgp.repaint();
			}
		}catch(Exception e){}
	}
	
	private void gameOver() {
		//game over Count
		try {
			for(int i=1;i<=9;i++){
				Thread.sleep(300);
				Data.overMsg="GAME OVER".substring(0, i);
				GameFrame.bgp.repaint();
			}
		} catch (Exception e) {}
		Block.getInstance().gameOver();
	}
	
	private void gameStart() {
		try{
			Thread.sleep(Data.levelSleep);
			Block.getInstance().down();
			GameFrame.bgp.repaint();
		}catch(Exception e){}
	}
	
	public void run(){
		while(true){
			try {Thread.sleep(1);} catch (InterruptedException e1) {}
			if(Data.gameOver)	gameOver();
			if(Data.startCount>0) startCount();		
			if(Data.status) gameStart(); 
		}
	}
}
