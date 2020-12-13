package com.client.common;

public class Data {
	/*map
	 x=0,11 y=21은 테두리
	 [][1~10][]
	 [][][0~20]
	 left/right-0 bottom-1 */
	public static boolean[][][] map = new boolean[2][12][21];
	
	/*0-x 1-y*/
	public static int[][] nowBlock = new int[2][4];
	

	public final static int BlockSize = 20;

	public static int idx=0;
	public static int saveIdx=-1;
	public static int spinIdx=0;
	public static int nextIdx1=0;
	public static int nextIdx2=0;
	public static int nextIdx3=0;
	public static int nextIdx4=0;
	public static int nextIdx5=0;
	
	public static int maxScore=0;
	public static int score=0;
	public static int lineX=0;
	public static int lineY=0;
	
	public static int level=0;
	public static int levelSleep=0;
	
	public static boolean status = false;
	public static boolean tSpin = false;
	public static boolean savestatus = false;
	public static boolean spacestatus = false;
	public static boolean gameOver = false;
	
	public static int startCount = 0;
	public static int startCountFontSize = 40;
	public static int comboCount = 0;
	public static int lineCount = 0;
	public static int lineTemp = -1;
	
	public static String overMsg = null;
	public static String clearMsg = null;
	public static String tSpinMsg = null;
	/* bX[] frist init
	 * >> newBlock();
	 * >> gameStart();
	 * >> changeBlock(); if(saveBlock!=null)
	 *   */
	
}
