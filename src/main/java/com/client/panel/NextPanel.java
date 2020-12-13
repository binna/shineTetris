package com.client.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.client.common.Block;
import com.client.common.Data;

public class NextPanel extends JPanel{
	
	public NextPanel(){
		setPreferredSize(new Dimension(250, 710));
		setBackground(new Color(255, 0, 0, 0));
	}
	
	@Override
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g);
		int bsX=50;
		int bsY=250;
		g.setColor(Color.white);
		g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 30));
		g.drawString("Level : "+Data.getInstance().level, 20, bsY-100);
		g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		g.drawString("[next]", 50, bsY-30);
		if(Data.getInstance().status){
			for (int i = 0; i < 4; i++) {
				int x1 = bsX + (Block.getInstance().bX[Data.getInstance().nextIdx1][i] * Data.getInstance().BlockSize);
				int y1 = bsY + (Block.getInstance().bY[Data.getInstance().nextIdx1][i] * Data.getInstance().BlockSize);
				int x2 = bsX + (Block.getInstance().bX[Data.getInstance().nextIdx2][i] * Data.getInstance().BlockSize);
				int y2 = bsY + (Block.getInstance().bY[Data.getInstance().nextIdx2][i] * Data.getInstance().BlockSize) + 60;
				int x3 = bsX + (Block.getInstance().bX[Data.getInstance().nextIdx3][i] * Data.getInstance().BlockSize);
				int y3 = bsY + (Block.getInstance().bY[Data.getInstance().nextIdx3][i] * Data.getInstance().BlockSize) + 120;
				int x4 = bsX + (Block.getInstance().bX[Data.getInstance().nextIdx4][i] * Data.getInstance().BlockSize);
				int y4 = bsY + (Block.getInstance().bY[Data.getInstance().nextIdx4][i] * Data.getInstance().BlockSize) + 180;
				int x5 = bsX + (Block.getInstance().bX[Data.getInstance().nextIdx5][i] * Data.getInstance().BlockSize);
				int y5 = bsY + (Block.getInstance().bY[Data.getInstance().nextIdx5][i] * Data.getInstance().BlockSize) + 240;

				g.setColor(Color.red);
				g.fillRect(x1, y1, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.white);
				g.drawRect(x1, y1, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.red);
				g.fillRect(x2, y2, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.white);
				g.drawRect(x2, y2, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.red);
				g.fillRect(x3, y3, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.white);
				g.drawRect(x3, y3, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.red);
				g.fillRect(x4, y4, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.white);
				g.drawRect(x4, y4, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.red);
				g.fillRect(x5, y5, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
				g.setColor(Color.white);
				g.drawRect(x5, y5, Data.getInstance().BlockSize, Data.getInstance().BlockSize);
		}}	
	}
}
