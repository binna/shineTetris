package com.client.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.client.socket.RankSerialize;
import com.client.common.Block;
import com.client.common.Data;

public class SavePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Font font=null;
	private Font status=null;
	private Font X = null;
	
	public SavePanel(){
		setPreferredSize(new Dimension(250, 710));
		setBackground(new Color(255, 0, 0, 0));
		font = new Font("Franklin Gothic Medium", Font.PLAIN, 20);
		status = new Font("Franklin Gothic Medium", Font.PLAIN, 14);
		X = new Font("Franklin Gothic Medium", Font.PLAIN, 50);
	}
	/*
	 * s: 시작 e: 끝 x: x좌표 y: y좌표
	 * size : 증감값
	 * bs = 블록시작위치 x, y
	 */
	int sX = 100;
	int sY = 220;
	
	int bsX=sX+Data.getInstance().BlockSize*2-8;
	int bsY=sY+Data.getInstance().BlockSize*2;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.setFont(status);
		g.drawString("SAVE : "+String.valueOf(Data.getInstance().savestatus).toUpperCase(), sX+17, sY-60);
		g.setFont(font);
		g.drawString("[SAVE]", sX+20, sY-20);
		
		// Block 다음블럭
		if(Data.getInstance().saveIdx!=-1){
		for(int i=0;i<4;i++){
			int x = bsX+(Block.getInstance().bX[Data.getInstance().saveIdx][i]*Data.getInstance().BlockSize);
			int y = bsY+(Block.getInstance().bY[Data.getInstance().saveIdx][i]*Data.getInstance().BlockSize)-20;
			
			g.setColor(Color.red);
			g.fillRect(x, y, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
			g.setColor(Color.white);
			g.drawRect(x, y, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
		}}else {
			g.setFont(X);
			g.drawString("?", sX+40, sY+40);
			
		}
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("MAX  : "+Data.getInstance().maxScore , sX-30, sY+200);
		g.drawString("SCORE : "+Data.getInstance().score , sX-30, sY+230);
		g.drawString(Data.getInstance().comboCount+" COMBO~",sX-30,sY+260);
		if(Data.getInstance().clearMsg!=null) {
			g.drawString(Data.getInstance().clearMsg, sX-30, sY+290);
		}
	}
}
