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
	private Block(){}
	private static class Singleton{
		private static final Block instance = new Block();
	}

	public static Block getInstance(){
		return Singleton.instance;
	}
	
	private int random() {
		int rNum = r.nextInt(7);
		
		while(rNum==Data.nextIdx2 || rNum==Data.nextIdx3 || rNum==Data.nextIdx4 || rNum==Data.nextIdx5) {
			rNum = r.nextInt(7);
		}
		
		return rNum;
		
	}
	
	private int spincheck(){
		/* �� .. �ٲܶ� ��� �ٲ�� ���� �𸣰ڴ� .*/
		int j=0;
		for (j = 0; j < bSpinX.length; j++) {
			int i = 0;
			for (i = 0; i < 4; i++) {
				try {
					if (Data.map[0][bX[Data.spinIdx][i] + Data.lineX + 1 + bSpinX[j]][bY[Data.spinIdx][i] + Data.lineY
							+ bSpinY[j]])
						break;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
			if (i == 4) {
				Data.idx = Data.spinIdx;
				Data.lineX=Data.lineX+bSpinX[j];
				Data.lineY=Data.lineY+bSpinY[j];
				switch(Data.idx) {
				case 4:
				case 16:
				case 17:
				case 18:
					Data.tSpin = true;
				}
				break;
			}
		}
		return j;
	}
	
	public void spin(){
		switch(Data.idx) {
		case 0: Data.spinIdx=0;break;
		case 1:	Data.spinIdx=7;break;
		case 2:	Data.spinIdx=10;break;
		case 3: Data.spinIdx=13;break;
		case 4: Data.spinIdx=16;break;
		case 5: Data.spinIdx=19;break;
		case 6: Data.spinIdx=22;break;
		case 7: Data.spinIdx=8;break;
		case 8: Data.spinIdx=9;break;
		case 9: Data.spinIdx=1;break;
		case 10: Data.spinIdx=11;break;
		case 11: Data.spinIdx=12;break;
		case 12: Data.spinIdx=2;break;
		case 13: Data.spinIdx=14;break;
		case 14: Data.spinIdx=15;break;
		case 15: Data.spinIdx=3;break;
		case 16: Data.spinIdx=17;break;
		case 17: Data.spinIdx=18;break;
		case 18: Data.spinIdx=4;break;
		case 19: Data.spinIdx=20;break;
		case 20: Data.spinIdx=21;break;
		case 21: Data.spinIdx=5;break;
		case 22: Data.spinIdx=23;break;
		case 23: Data.spinIdx=24;break;
		case 24: Data.spinIdx=6;break;
		default:break;
		}
		int idx = spincheck();
		if(idx<bSpinX.length) blockSetting();
	}
	
	public void reverseSpin() {
		switch(Data.idx) {
		case 0: Data.spinIdx=0;break;
		case 1:	Data.spinIdx=9;break;
		case 2:	Data.spinIdx=12;break;
		case 3: Data.spinIdx=15;break;
		case 4: Data.spinIdx=18;break;
		case 5: Data.spinIdx=21;break;
		case 6: Data.spinIdx=24;break;
		case 7: Data.spinIdx=1;break;
		case 8: Data.spinIdx=7;break;
		case 9: Data.spinIdx=8;break;
		case 10: Data.spinIdx=2;break;
		case 11: Data.spinIdx=10;break;
		case 12: Data.spinIdx=11;break;
		case 13: Data.spinIdx=3;break;
		case 14: Data.spinIdx=13;break;
		case 15: Data.spinIdx=14;break;
		case 16: Data.spinIdx=4;break;
		case 17: Data.spinIdx=16;break;
		case 18: Data.spinIdx=17;break;
		case 19: Data.spinIdx=5;break;
		case 20: Data.spinIdx=19;break;
		case 21: Data.spinIdx=20;break;
		case 22: Data.spinIdx=6;break;
		case 23: Data.spinIdx=22;break;
		case 24: Data.spinIdx=23;break;
		default:break;
		}
		int idx = spincheck();
		if(idx<bSpinX.length) blockSetting();
	}
	
	/* real block setting*/
	private void blockSetting() {
		for(int i=0;i<4;i++) {
			Data.nowBlock[0][i]=bX[Data.idx][i]+Data.lineX;
			Data.nowBlock[1][i]=bY[Data.idx][i]+Data.lineY;
		}
	}

	
	private void newBlock(){
		Data.savestatus=true;
		Data.idx=Data.nextIdx1;
		Data.nextIdx1=Data.nextIdx2;
		Data.nextIdx2=Data.nextIdx3;
		Data.nextIdx3=Data.nextIdx4;
		Data.nextIdx4=Data.nextIdx5;
		Data.nextIdx5=random();
		Data.lineX=4;
		Data.lineY=0;
		blockSetting();
	}

	public void saveBlock(){
		if(Data.saveIdx==-1) {
			Data.saveIdx=Data.idx;
			Data.idx=Data.nextIdx1;
			Data.nextIdx1=Data.nextIdx2;
			Data.nextIdx2=Data.nextIdx3;
			Data.nextIdx3=Data.nextIdx4;
			Data.nextIdx4=Data.nextIdx5;
			Data.nextIdx5=random();
		}else {
			int tmp = Data.saveIdx;
			Data.saveIdx=Data.idx;
			Data.idx=tmp;
		}
		switch(Data.saveIdx) {
		case	0:	Data.saveIdx=0; break;
		case	1:
		case	7:
		case	8:
		case	9: Data.saveIdx=1; break;
		case	2:
		case	10:
		case	11:
		case	12: Data.saveIdx=2; break;
		case	3:
		case	13:
		case	14:
		case	15: Data.saveIdx=3; break;
		case	4:
		case	16:
		case	17:
		case	18: Data.saveIdx=4; break;
		case	5:
		case	19:
		case	20:
		case	21: Data.saveIdx=5; break;
		case	6:
		case	22:
		case	23:
		case	24: Data.saveIdx=6; break;
		}
		
		Data.lineX=4;
		Data.lineY=0;
		blockSetting();
		// only 1 save
		Data.savestatus=false;
	}
	
	public void gameHold() {
		Data.status=false;
		CenterPanel.btnHold.setVisible(true);
	}

	public void startCount() {
		Data.startCount=3;
		Data.startCountFontSize=260;
	}
	
	public void gameStart() {
		Data.tSpinMsg = null;
		Data.status = true;
		Data.savestatus = true;
		Data.comboCount=0;
		Data.lineCount = 0;
		Data.lineTemp = -1;
		Data.level=0;
		Data.levelSleep=1500;
		Data.saveIdx=-1;
		Data.score=0;
		Data.idx=random();
		Data.nextIdx1=random();
		Data.nextIdx2=random();
		Data.nextIdx3=random();
		Data.nextIdx4=random();
		Data.nextIdx5=random();
		Data.lineX=4;
		Data.lineY=0;
		Data.clearMsg=null;
		Data.overMsg = null;
		blockSetting();
		/* init map */
		for(int i=0;i<12;i++){
			for(int j=0;j<21;j++){
				Data.map[0][i][j]=false;
				Data.map[1][i][j]=false;
		}}
		/* x, y */
		for(int i=0;i<12;i++){
			Data.map[0][i][20]=true;//
			Data.map[1][i][20]=true;
		}
		for(int i=0;i<21;i++){
			Data.map[0][0][i]=true;
			Data.map[0][11][i]=true;
			Data.map[1][0][i]=true;//
			Data.map[1][11][i]=true;//
		}
	}
	
	public void gameOver() {
		Data.gameOver = false;
		Data.status = false;
		if(Data.score>Data.maxScore) {
			Data.maxScore=Data.score;
		}
		CenterPanel.btn.setVisible(true);
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
				if(Data.map[1][Data.nowBlock[0][i]+1][Data.nowBlock[1][i]+1])break;
			}break;
		case "left":
			for(i=0;i<4;i++){
				if(Data.map[0][Data.nowBlock[0][i]][Data.nowBlock[1][i]])break;
			}break;
		case "right":
			for(i=0;i<4;i++){
				if(Data.map[0][Data.nowBlock[0][i]+2][Data.nowBlock[1][i]])break;
			}break;
		}
		
		/* check false */
		if(i==4){
			switch (str) {
			case "down":
				Data.lineY++;
				for (i = 0; i < 4; i++) {
					Data.nowBlock[1][i]++;
				}break;
			case "left":
				Data.lineX--;
				for (i = 0; i < 4; i++) {
					Data.nowBlock[0][i]--;
				}break;
			case "right":
				Data.lineX++;
				for (i = 0; i < 4; i++) {
					Data.nowBlock[0][i]++;
				}break;
			}
			
		}else{
			if(str.equals("down")) clear(str);
		}
	}
	
	private void clear(String str) {
		/* check true */
		Data.spacestatus = false;
		gameOverCheck();
		if (Data.status) {
			clearCheck();
			if(Data.tSpin) Data.tSpin =false;		
			newBlock();
		}
	}
	
	/* ���� ������ ���� üũ */
	private void gameOverCheck() {
		for (int i = 0; i < 4; i++) {
			Data.map[0][Data.nowBlock[0][i] + 1][Data.nowBlock[1][i]] = true;
			Data.map[1][Data.nowBlock[0][i] + 1][Data.nowBlock[1][i]] = true;

			if (Data.nowBlock[0][i] + 1 > 3 && Data.nowBlock[0][i] <= 7 && Data.nowBlock[1][i] == 0) {
				Data.gameOver = true; // first line is game over....
				Data.status = false;
			}
		}
	}
	
	/* ���� ���� */
	
	private void clearCheck() {
		int i = 0;
		/* line clear */
		int combo = 0; // �ѹ��� ��� �����ߴ��� üũ
		for (int j = 0; j < 4; j++) {
			for (i = 1; i < 11; i++) {
				if (!Data.map[0][i][Data.nowBlock[1][j]]) {
					break;
				} else if (i == 10) {
					// clear line
					Data.lineTemp = Data.nowBlock[1][j];
					combo++;
					Data.comboCount++;
					for (int kx = 1; kx < 11; kx++) {
						Data.map[0][kx][Data.nowBlock[1][j]] = false;
						Data.map[1][kx][Data.nowBlock[1][j]] = false;
					}
					
					/* each down 1line */
					for (int ky = Data.nowBlock[1][j]; ky >= 0; ky--) {
						for (int kx = 1; kx < 11; kx++) {
							if (Data.map[0][kx][ky]) {
								Data.map[0][kx][ky] = false;
								Data.map[1][kx][ky] = false;
								Data.map[0][kx][ky + 1] = true;
								Data.map[1][kx][ky + 1] = true;
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
			Data.clearMsg = combo + " line clear!";
			if(Data.tSpin) {
				switch(combo) {
				case 1:	Data.tSpinMsg = "  Single T-spin!"; break;
				case 2: Data.tSpinMsg = "Back To Back~!"; break;
				case 3: Data.tSpinMsg = " Triple T-spin!"; break;
				}
			}
		} else {
			Data.tSpinMsg = null;
			Data.lineTemp = -1;
			Data.comboCount = 0;
		}
		Data.lineCount = (int) Math.pow(2, combo);
		Data.score += (Math.pow(2, combo) + Data.comboCount);
		// 15점마다 1씩증가
		Data.level = Data.score / 15 + 1;
		if (Data.levelSleep > 100) {
			Data.levelSleep = 1600 - (Data.level * 50);
		} else {
			if (Data.level > 25) Data.levelSleep = 50;
			else if (Data.level > 32) Data.levelSleep = 30;
		}
	}
}
