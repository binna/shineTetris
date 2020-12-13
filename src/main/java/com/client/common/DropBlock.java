package com.client.common;

public class DropBlock extends Thread{
	
	private void startCount() {
		try{
			for(int i=0;i<20;i++){
				Data.getInstance().startCountFontSize-=7;
				Thread.sleep(50);
				GameFrame.bgp.repaint();
			}
			Data.getInstance().startCountFontSize=260;
			Data.getInstance().startCount--;
			if(Data.getInstance().startCount==0){
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
				Data.getInstance().overMsg="GAME OVER".substring(0, i);
				GameFrame.bgp.repaint();
			}
		} catch (Exception e) {}
		Block.getInstance().gameOver();
	}
	
	private void gameStart() {
		try{
			Thread.sleep(Data.getInstance().levelSleep);
			Block.getInstance().down();
			GameFrame.bgp.repaint();
		}catch(Exception e){}
	}
	
	public void run(){
		
		while(true){
			try {Thread.sleep(10);} catch (InterruptedException e1) {System.out.println("DropBlock Exit");}
			if(Data.getInstance().gameOver)	gameOver();
			if(Data.getInstance().startCount>0) startCount();		
			if(Data.getInstance().status) gameStart(); 
		}
	}
	
}
