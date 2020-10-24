package com.client.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	ImageIcon bgimg;
	public BackGroundPanel() {
		setPreferredSize(new Dimension(840, 710));
		setLayout(new BorderLayout());
	}
	public void paintComponent(Graphics g) {
        bgimg = new ImageIcon("src/img/bgimg.jpg");
        g.drawImage(bgimg.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
        
        setOpaque(false);//그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
    }
}
