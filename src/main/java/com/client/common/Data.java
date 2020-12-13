package com.client.common;

public class Data {
	/*map
	 x=0,11 y=21은 테두리
	 [][1~10][]
	 [][][0~20]
	 left/right-0 bottom-1 */
	public boolean[][][] map = new boolean[2][12][21];
	
	/*0-x 1-y*/
	public int[][] nowBlock = new int[2][4];
	

	public final int BlockSize = 20;

	public int idx=0;
	public int saveIdx=-1;
	public int spinIdx=0;
	public int nextIdx1=0;
	public int nextIdx2=0;
	public int nextIdx3=0;
	public int nextIdx4=0;
	public int nextIdx5=0;
	
	public int maxScore=0;
	public int score=0;
	public int lineX=0;
	public int lineY=0;
	
	public int level=0;
	public int levelSleep=0;
	
	public boolean status = false;
	public boolean tSpin = false;
	public boolean savestatus = false;
	public boolean spacestatus = false;
	public boolean gameOver = false;
	
	public int startCount = 0;
	public int startCountFontSize = 40;
	public int comboCount = 0;
	public int lineCount = 0;
	public int lineTemp = -1;
	
	public String overMsg = null;
	public String clearMsg = null;
	public String tSpinMsg = null;
	/* bX[] frist init
	 * >> newBlock();
	 * >> gameStart();
	 * >> changeBlock(); if(saveBlock!=null)
	 *   */
	
	private volatile static Data instance;
	
//	static {
//		instance = new Data();
//	}
	
	private Data(){}
	
	public static Data getInstance(){
		if(instance == null) {
			synchronized (Data.class) {
				if(instance == null) {
					instance = new Data();
				}
			}
		}
		return instance;
	}
	
	public void distroy() {
		instance = null;
	}
	
}
