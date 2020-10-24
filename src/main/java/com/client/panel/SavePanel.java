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
		font = new Font("Jokerman", Font.ITALIC, 20);
		status = new Font("Jokerman", Font.ITALIC, 13);
		X = new Font("���� ���", Font.ITALIC, 50);
	}
	/*
	 * s: 시작 e: 끝 x: x좌표 y: y좌표
	 * size : 증감값
	 * bs = 블록시작위치 x, y
	 */
	int sX = 100;
	int sY = 220;
	
	int bsX=sX+Data.BlockSize*2-8;
	int bsY=sY+Data.BlockSize*2;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.setFont(status);
		g.drawString("SAVE : "+String.valueOf(Data.savestatus).toUpperCase(), sX+17, sY-60);
		g.setFont(font);
		g.drawString("[SAVE]", sX+20, sY-20);
		
		// Block
		if(Data.saveIdx!=-1){
		for(int i=0;i<4;i++){
			int x = bsX+(Block.getInstance().bX[Data.saveIdx][i]*Data.BlockSize);
			int y = bsY+(Block.getInstance().bY[Data.saveIdx][i]*Data.BlockSize)-20;
			
			g.setColor(Color.red);
			g.fillRect(x, y, Data.BlockSize, Data.BlockSize);
			g.setColor(Color.white);
			g.drawRect(x, y, Data.BlockSize, Data.BlockSize);
		}}else {
			g.setFont(X);
			g.drawString("?", sX+40, sY+40);
			
		}
		g.setColor(Color.black);
		g.setFont(font);
		g.drawString("MAX  : "+Data.maxScore , sX-30, sY+200);
		g.drawString("SCORE : "+Data.score , sX-30, sY+230);
		g.drawString(Data.comboCount+" COMBO~",sX-30,sY+260);
		if(Data.clearMsg!=null) {
			g.drawString(Data.clearMsg, sX-30, sY+290);
		}
	}
}
