package com.client.common;

import java.util.Random;

import javax.net.ssl.SSLEngineResult.Status;

import com.client.panel.CenterPanel;

public class Block {
	
	Random r = new Random();
		public final int[][] bX ={
				{0,1,0,1},{-1,0,1,2},{0,0,1,2},{2,2,1,0},{0,1,1,2},{0,1,1,2},{2,1,1,0}, //0-6
				{1,1,1,1},{-1,0,1,2},{0,0,0,0}, //7~9
				{1,2,1,1},{0,1,2,2},{1,1,1,0}, //10~12
				{1,1,1,2},{0,1,2,0},{0,1,1,1}, //13~15
				{1,1,2,1},{0,1,2,1},{0,1,1,1}, //16~18
				{1,0,1,0},{0,1,1,2},{0,-1,0,-1}, //19~21
				{1,1,2,2},{2,1,1,0},{0,0,1,1} //22~24
		};
		public final int[][] bY={
				{0,0,1,1},{0,0,0,0},{0,1,1,1},{0,1,1,1},{1,1,0,1},{0,0,1,1},{0,0,1,1}, //0~6
				{0,1,2,3},{1,1,1,1},{0,1,2,3}, //7~9
				{0,0,1,2},{1,1,1,2},{0,1,2,2}, //8~10
				{0,1,2,2},{1,1,1,2},{0,0,1,2}, //11~13
				{0,1,1,2},{1,1,1,2},{1,0,1,2}, //14~16
				{0,1,1,2},{1,1,2,2},{0,1,1,2}, //19~21
				{0,1,1,2},{1,1,2,2},{0,1,1,2} //22~24
		};
		
		/*���ư��°�*/
		public final int[] bSpinX= {0,1,-1,0,1,-1,0,1,-1};
		public final int[] bSpinY= {0,0,0,1,1,1,2,2,2
				
		};
		
		/*  
		 * 
		 [0]┌┐  [1] ─  [2]└───[3]───┘ [4]┴  [5]─┐  [6]┌─    
		 	└┘ 			   		  				└─   ─┘
		 		   ↓	  ↓ 	  ↓	    ↓		↓	  ↓	   
	total : 25	[7] │  [10]┌─  [13]│  [16]├ [19]┌┘ [22]└┐
		 				   │	   └─
	8,20,23(y-1)[8] -  [11]──┐ [14]┌──[17]┬	[20]─┐ [23]┌─
		 		 								 └─   ─┘
	18,21,24(x-1)[9] | [12] │ [15]─┐  [18]┤ [21]┌┘ [24]└┐
		   	 	 	   	   ─┘	   │ 
		
		 */
//	private Block(){}
//	private static class Singleton{
//		private static final Block instance = new Block();
//	}
//
//	public static Block getInstance(){
//		return Singleton.instance;
//	}
	
		
	private volatile static Block instance;
	
//	static {
//		instance = new Block();
//	}
	
	private Block(){}
	
//	public static synchronized Block getInstance(){
	public static Block getInstance(){
		if(instance == null) {
			synchronized (Block.class) {
				if(instance == null) {
					instance = new Block();
				}
			}
		}
		
		return instance;
	}
	
	public void distroy() {
		instance = null;
	}
	
	private int random() {
		int rNum = r.nextInt(7);
		
		while(rNum==Data.getInstance().nextIdx2 || rNum==Data.getInstance().nextIdx3 || rNum==Data.getInstance().nextIdx4 || rNum==Data.getInstance().nextIdx5) {
			rNum = r.nextInt(7);
		}
		
		return rNum;
		
	}
	
	private int spincheck(){
		
		int j=0;
		for (j = 0; j < bSpinX.length; j++) {
			int i = 0;
			for (i = 0; i < 4; i++) {
				try {
					if (Data.getInstance().map[0][bX[Data.getInstance().spinIdx][i] + Data.getInstance().lineX + 1 + bSpinX[j]][bY[Data.getInstance().spinIdx][i] + Data.getInstance().lineY
							+ bSpinY[j]])
						break;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
			if (i == 4) {
				Data.getInstance().idx = Data.getInstance().spinIdx;
				Data.getInstance().lineX=Data.getInstance().lineX+bSpinX[j];
				Data.getInstance().lineY=Data.getInstance().lineY+bSpinY[j];
				switch(Data.getInstance().idx) {
				case 4:
				case 16:
				case 17:
				case 18:
					Data.getInstance().tSpin = true;
				}
				break;
			}
		}
		return j;
	}
	
	public void spin(){
		switch(Data.getInstance().idx) {
		case 0: Data.getInstance().spinIdx=0;break;
		case 1:	Data.getInstance().spinIdx=7;break;
		case 2:	Data.getInstance().spinIdx=10;break;
		case 3: Data.getInstance().spinIdx=13;break;
		case 4: Data.getInstance().spinIdx=16;break;
		case 5: Data.getInstance().spinIdx=19;break;
		case 6: Data.getInstance().spinIdx=22;break;
		case 7: Data.getInstance().spinIdx=8;break;
		case 8: Data.getInstance().spinIdx=9;break;
		case 9: Data.getInstance().spinIdx=1;break;
		case 10: Data.getInstance().spinIdx=11;break;
		case 11: Data.getInstance().spinIdx=12;break;
		case 12: Data.getInstance().spinIdx=2;break;
		case 13: Data.getInstance().spinIdx=14;break;
		case 14: Data.getInstance().spinIdx=15;break;
		case 15: Data.getInstance().spinIdx=3;break;
		case 16: Data.getInstance().spinIdx=17;break;
		case 17: Data.getInstance().spinIdx=18;break;
		case 18: Data.getInstance().spinIdx=4;break;
		case 19: Data.getInstance().spinIdx=20;break;
		case 20: Data.getInstance().spinIdx=21;break;
		case 21: Data.getInstance().spinIdx=5;break;
		case 22: Data.getInstance().spinIdx=23;break;
		case 23: Data.getInstance().spinIdx=24;break;
		case 24: Data.getInstance().spinIdx=6;break;
		default:break;
		}
		int idx = spincheck();
		if(idx<bSpinX.length) blockSetting();
	}
	
	public void reverseSpin() {
		switch(Data.getInstance().idx) {
		case 0: Data.getInstance().spinIdx=0;break;
		case 1:	Data.getInstance().spinIdx=9;break;
		case 2:	Data.getInstance().spinIdx=12;break;
		case 3: Data.getInstance().spinIdx=15;break;
		case 4: Data.getInstance().spinIdx=18;break;
		case 5: Data.getInstance().spinIdx=21;break;
		case 6: Data.getInstance().spinIdx=24;break;
		case 7: Data.getInstance().spinIdx=1;break;
		case 8: Data.getInstance().spinIdx=7;break;
		case 9: Data.getInstance().spinIdx=8;break;
		case 10: Data.getInstance().spinIdx=2;break;
		case 11: Data.getInstance().spinIdx=10;break;
		case 12: Data.getInstance().spinIdx=11;break;
		case 13: Data.getInstance().spinIdx=3;break;
		case 14: Data.getInstance().spinIdx=13;break;
		case 15: Data.getInstance().spinIdx=14;break;
		case 16: Data.getInstance().spinIdx=4;break;
		case 17: Data.getInstance().spinIdx=16;break;
		case 18: Data.getInstance().spinIdx=17;break;
		case 19: Data.getInstance().spinIdx=5;break;
		case 20: Data.getInstance().spinIdx=19;break;
		case 21: Data.getInstance().spinIdx=20;break;
		case 22: Data.getInstance().spinIdx=6;break;
		case 23: Data.getInstance().spinIdx=22;break;
		case 24: Data.getInstance().spinIdx=23;break;
		default:break;
		}
		int idx = spincheck();
		if(idx<bSpinX.length) blockSetting();
	}
	
	/* real block setting*/
	private void blockSetting() {
		for(int i=0;i<4;i++) {
			Data.getInstance().nowBlock[0][i]=bX[Data.getInstance().idx][i]+Data.getInstance().lineX;
			Data.getInstance().nowBlock[1][i]=bY[Data.getInstance().idx][i]+Data.getInstance().lineY;
		}
	}

	
	private void newBlock(){
		Data.getInstance().savestatus=true;
		Data.getInstance().idx=Data.getInstance().nextIdx1;
		Data.getInstance().nextIdx1=Data.getInstance().nextIdx2;
		Data.getInstance().nextIdx2=Data.getInstance().nextIdx3;
		Data.getInstance().nextIdx3=Data.getInstance().nextIdx4;
		Data.getInstance().nextIdx4=Data.getInstance().nextIdx5;
		Data.getInstance().nextIdx5=random();
		Data.getInstance().lineX=4;
		Data.getInstance().lineY=0;
		blockSetting();
	}

	public void saveBlock(){
		if(Data.getInstance().saveIdx==-1) {
			Data.getInstance().saveIdx=Data.getInstance().idx;
			Data.getInstance().idx=Data.getInstance().nextIdx1;
			Data.getInstance().nextIdx1=Data.getInstance().nextIdx2;
			Data.getInstance().nextIdx2=Data.getInstance().nextIdx3;
			Data.getInstance().nextIdx3=Data.getInstance().nextIdx4;
			Data.getInstance().nextIdx4=Data.getInstance().nextIdx5;
			Data.getInstance().nextIdx5=random();
		}else {
			int tmp = Data.getInstance().saveIdx;
			Data.getInstance().saveIdx=Data.getInstance().idx;
			Data.getInstance().idx=tmp;
		}
		switch(Data.getInstance().saveIdx) {
		case	0:	Data.getInstance().saveIdx=0; break;
		case	1:
		case	7:
		case	8:
		case	9: Data.getInstance().saveIdx=1; break;
		case	2:
		case	10:
		case	11:
		case	12: Data.getInstance().saveIdx=2; break;
		case	3:
		case	13:
		case	14:
		case	15: Data.getInstance().saveIdx=3; break;
		case	4:
		case	16:
		case	17:
		case	18: Data.getInstance().saveIdx=4; break;
		case	5:
		case	19:
		case	20:
		case	21: Data.getInstance().saveIdx=5; break;
		case	6:
		case	22:
		case	23:
		case	24: Data.getInstance().saveIdx=6; break;
		}
		
		Data.getInstance().lineX=4;
		Data.getInstance().lineY=0;
		blockSetting();
		// only 1 save
		Data.getInstance().savestatus=false;
	}
	
	public void gameHold() {
		Data.getInstance().status=false;
		CenterPanel.btnHold.setVisible(true);
	}

	public void startCount() {
		Data.getInstance().startCount=3;
		Data.getInstance().startCountFontSize=260;
	}
	
	public void gameStart() {
		Data.getInstance().tSpinMsg = null;
		Data.getInstance().status = true;
		Data.getInstance().savestatus = true;
		Data.getInstance().comboCount=0;
		Data.getInstance().lineCount = 0;
		Data.getInstance().lineTemp = -1;
		Data.getInstance().level=0;
		Data.getInstance().levelSleep=1500;
		Data.getInstance().saveIdx=-1;
		Data.getInstance().score=0;
		Data.getInstance().idx=random();
		Data.getInstance().nextIdx1=random();
		Data.getInstance().nextIdx2=random();
		Data.getInstance().nextIdx3=random();
		Data.getInstance().nextIdx4=random();
		Data.getInstance().nextIdx5=random();
		Data.getInstance().lineX=4;
		Data.getInstance().lineY=0;
		Data.getInstance().clearMsg=null;
		Data.getInstance().overMsg = null;
		blockSetting();
		/* init map */
		for(int i=0;i<12;i++){
			for(int j=0;j<21;j++){
				Data.getInstance().map[0][i][j]=false;
				Data.getInstance().map[1][i][j]=false;
		}}
		/* x, y */
		for(int i=0;i<12;i++){
			Data.getInstance().map[0][i][20]=true;//
			Data.getInstance().map[1][i][20]=true;
		}
		for(int i=0;i<21;i++){
			Data.getInstance().map[0][0][i]=true;
			Data.getInstance().map[0][11][i]=true;
			Data.getInstance().map[1][0][i]=true;//
			Data.getInstance().map[1][11][i]=true;//
		}
	}
	
	public void gameOver() {
		Data.getInstance().gameOver = false;
		Data.getInstance().status = false;
		if(Data.getInstance().score>Data.getInstance().maxScore) {
			Data.getInstance().maxScore=Data.getInstance().score;
		}
		CenterPanel.btn.setVisible(true);
		CenterPanel.Exitbtn.setVisible(true);
		GameFrame.bgp.repaint();
	}
	
	public void left(){
		move("left");
	}
	public void right(){
		move("right");
	}
	
	public void down() {
		move("down");
	}
	
	private void move(String str){
		int i=0;
		/* move check */
		switch(str){
		case "down":
			for(i=0;i<4;i++){
				if(Data.getInstance().map[1][Data.getInstance().nowBlock[0][i]+1][Data.getInstance().nowBlock[1][i]+1])break;
			}break;
		case "left":
			for(i=0;i<4;i++){
				if(Data.getInstance().map[0][Data.getInstance().nowBlock[0][i]][Data.getInstance().nowBlock[1][i]])break;
			}break;
		case "right":
			for(i=0;i<4;i++){
				if(Data.getInstance().map[0][Data.getInstance().nowBlock[0][i]+2][Data.getInstance().nowBlock[1][i]])break;
			}break;
		}
		
		/* check false */
		if(i==4){
			switch (str) {
			case "down":
				Data.getInstance().lineY++;
				for (i = 0; i < 4; i++) {
					Data.getInstance().nowBlock[1][i]++;
				}break;
			case "left":
				Data.getInstance().lineX--;
				for (i = 0; i < 4; i++) {
					Data.getInstance().nowBlock[0][i]--;
				}break;
			case "right":
				Data.getInstance().lineX++;
				for (i = 0; i < 4; i++) {
					Data.getInstance().nowBlock[0][i]++;
				}break;
			}
			
		}else{
			if(str.equals("down")) clear(str);
		}
	}
	
	private void clear(String str) {
		/* check true */
		Data.getInstance().spacestatus = false;
		gameOverCheck();
		if (Data.getInstance().status) {
			clearCheck();
			if(Data.getInstance().tSpin) Data.getInstance().tSpin =false;		
			newBlock();
		}
	}
	
	/* 블럭이 맨위에 인지 체크 */
	private void gameOverCheck() {
		for (int i = 0; i < 4; i++) {
			Data.getInstance().map[0][Data.getInstance().nowBlock[0][i] + 1][Data.getInstance().nowBlock[1][i]] = true;
			Data.getInstance().map[1][Data.getInstance().nowBlock[0][i] + 1][Data.getInstance().nowBlock[1][i]] = true;

			if (Data.getInstance().nowBlock[0][i] + 1 > 3 && Data.getInstance().nowBlock[0][i] <= 7 && Data.getInstance().nowBlock[1][i] == 0) {
				Data.getInstance().gameOver = true; // first line is game over....
				Data.getInstance().status = false;
			}
		}
	}
	
	/* 라인 삭제 */
	
	private void clearCheck() {
		int i = 0;
		/* line clear */
		int combo = 0; //한번에 몇개를 삭제했는지 체크
		for (int j = 0; j < 4; j++) {
			for (i = 1; i < 11; i++) {
				if (!Data.getInstance().map[0][i][Data.getInstance().nowBlock[1][j]]) {
					break;
				} else if (i == 10) {
					// clear line
					Data.getInstance().lineTemp = Data.getInstance().nowBlock[1][j];
					combo++;
					Data.getInstance().comboCount++;
					for (int kx = 1; kx < 11; kx++) {
						Data.getInstance().map[0][kx][Data.getInstance().nowBlock[1][j]] = false;
						Data.getInstance().map[1][kx][Data.getInstance().nowBlock[1][j]] = false;
					}
					
					/* each down 1line */
					for (int ky = Data.getInstance().nowBlock[1][j]; ky >= 0; ky--) {
						for (int kx = 1; kx < 11; kx++) {
							if (Data.getInstance().map[0][kx][ky]) {
								Data.getInstance().map[0][kx][ky] = false;
								Data.getInstance().map[1][kx][ky] = false;
								Data.getInstance().map[0][kx][ky + 1] = true;
								Data.getInstance().map[1][kx][ky + 1] = true;
							}
						}
					}
				}
			}
		}
		comboCount(combo);
	}
	
	
	/* 콤보 설정 */
	private void comboCount(int combo) {
		if (combo > 0) {
			Data.getInstance().clearMsg = combo + " line clear!";
			if(Data.getInstance().tSpin) {
				switch(combo) {
				case 1:	Data.getInstance().tSpinMsg = "  Single T-spin!"; break;
				case 2: Data.getInstance().tSpinMsg = "Back To Back~!"; break;
				case 3: Data.getInstance().tSpinMsg = " Triple T-spin!"; break;
				}
			}
		} else {
			Data.getInstance().tSpinMsg = null;
			Data.getInstance().lineTemp = -1;
			Data.getInstance().comboCount = 0;
		}
		Data.getInstance().lineCount = (int) Math.pow(2, combo);
		Data.getInstance().score += (Math.pow(2, combo) + Data.getInstance().comboCount);
		// 15점마다 1씩증가
		Data.getInstance().level = Data.getInstance().score / 15 + 1;
		if (Data.getInstance().levelSleep > 100) {
			Data.getInstance().levelSleep = 1600 - (Data.getInstance().level * 50);
		} else {
			if (Data.getInstance().level > 25) Data.getInstance().levelSleep = 50;
			else if (Data.getInstance().level > 32) Data.getInstance().levelSleep = 30;
		}
	}
}
