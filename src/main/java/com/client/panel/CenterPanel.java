package com.client.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.client.common.Block;
import com.client.common.Data;
import com.client.common.GameFrame;

public class CenterPanel extends JPanel {
	public static JButton btn;
	public static JButton btnHold;
	/*
	 * s: 시작 e: 끝 x: x좌표 y: y좌표
	 * size : 증감값
	 * x y : block start
	 */
	int sX = 80;
	int sY = 140;
	int eX = 280;
	int eY = 540;

	public CenterPanel() {
		setPreferredSize(new Dimension(340, 710));
		setBackground(new Color(255, 0, 0, 0));
		setLayout(null);
		btn=new JButton("START");
		btn.setLocation(130,320);
		btn.setSize(100,30);
		
		btnHold=new JButton("HOLD");
		btnHold.setLocation(130,320);
		btnHold.setSize(100,30);
		
		add(btn);
		add(btnHold);
	}// 생성자
	
 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// M A P 
		g.setColor(Color.black);
		g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 40));
//		ImageIcon bgimg = new ImageIcon("src/img/tetrisTitle.jpg");
//	    g.drawImage(bgimg.getImage(), 0, 25, 350, 100, this);
		//g.drawString("T E T R I S", sX, sY-40);
		g.fillRect(sX, sY, eX - sX, eY - sY);
		g.setColor(Color.gray);
		for (int i = 1; i <= 19; i++) {
			g.drawLine(sX, sY + (Data.BlockSize * i), eX, sY + (Data.BlockSize * i));
		}

		for (int i = 1; i <= 9; i++) {
			g.drawLine(sX + (Data.BlockSize * i), sY, sX + (Data.BlockSize * i), eY);
		}
		
		//M A P block x좌표는 -20 
		for(int i=0;i<12;i++){
			for(int j=0;j<21;j++){
				if(Data.map[0][i][j]&&Data.map[1][i][j]){
					int mx = sX-Data.BlockSize+(Data.BlockSize*i);
					int my = sY+(Data.BlockSize*j);
					g.setColor(Color.darkGray);
					g.fillRect(mx, my, Data.BlockSize, Data.BlockSize);
					g.setColor(Color.white);
					g.drawRect(mx, my, Data.BlockSize, Data.BlockSize);
				}
		}}
		
		//게임시작  count
		if(Data.startCount!=0) {
			g.setColor(Color.red);
			g.setFont(new Font("Franklin Gothic Medium", Font.ITALIC,Data.startCountFontSize));
			g.drawString(String.valueOf(Data.startCount), 110, 400);
		}else if(Data.overMsg!=null) {
			//gameover 타이틀
			g.setColor(new Color(173, 173, 173));
			g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN,50));
			g.drawString(Data.overMsg, 50, 280);
//			paintComponentString(Data.overMsg, -198, 280, g, Color.GRAY, new Color(211, 211, 211));
		}
		
		//안쪽 블럭
		if(Data.status) {
			for(int i=0;i<4;i++) {
				int bx = sX+(Data.BlockSize*Data.nowBlock[0][i]);
				int by = sY+(Data.BlockSize*Data.nowBlock[1][i]);
				g.setColor(Color.blue);
				g.fillRect(bx, by, Data.BlockSize, Data.BlockSize);
				g.setColor(Color.white);
				g.drawRect(bx, by, Data.BlockSize, Data.BlockSize);
			}
		}
		
		//Score
		g.setFont(new Font("Bahnschrift", Font.BOLD, 20));	
		g.setColor(Color.MAGENTA);
		if(Data.lineTemp>-1) {		
			g.drawString("+"+Data.lineCount, 310, sY+(Data.lineTemp*Data.BlockSize));
			g.drawString("+"+Data.comboCount, 320, sY+(Data.lineTemp*Data.BlockSize)+20);		
		}
		
		//라인클리어시 나오는 점수 TSPIN
		if(Data.tSpinMsg!=null) {		
			g.setFont(new Font("Bahnschrift", Font.ITALIC, 30));		
			g.setColor(Color.MAGENTA);
			g.drawString(Data.tSpinMsg, 70, 200);
		}
		
		//Combo MSG
		g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 50));		
		if(Data.comboCount>0&&Data.comboCount<4) {
//			g.setColor(new Color(196,156,72));
//			g.drawString("Good!", 110, 650);
			paintComponentString("Good!", -135, 600, g, new Color(196,156,72), new Color(211, 211, 211));
		}else if(Data.comboCount>3&&Data.comboCount<7) {		
//			g.setColor(new Color(216,216,216));
//			g.drawString("Very Good!!", 70, 650);
			paintComponentString("Very Good!!", -185, 600, g, new Color(216,216,216), new Color(211, 211, 211));
		}else if(Data.comboCount>6){		
//			g.setColor(new Color(200,159,35));
//			g.drawString("Excellent!!!", 70, 650);
			paintComponentString("Excellent!!!!", -185, 600, g, new Color(200,159,35), new Color(211, 211, 211));
		}

	}//paint end
	private void paintComponentString(String text, int x, int y, Graphics g, Color textColor, Color outLineColor) {
		Graphics2D g2d = (Graphics2D)g;
        AffineTransform transform = g2d.getTransform();
        FontRenderContext frc = g2d.getFontRenderContext();
		
		TextLayout tl = new TextLayout(text, g.getFont(), frc);
		Shape shape = tl.getOutline(null);
		transform.translate(x, y);
	    g2d.transform(transform);
	    g2d.setColor(outLineColor);
		g2d.setStroke(new BasicStroke(2f));
	    g2d.draw(shape);
	    g2d.setColor(textColor);
	    g2d.fill(shape);			
	}
	
}
