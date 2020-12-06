package com.client.menu;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.client.socket.RankSerialize;

@SuppressWarnings("serial")
public class RankkingFrame extends JFrame{
	ArrayList<Object> list = null;
	private RankSerialize sil =null;
	
	public RankkingFrame(ArrayList<Object> list) {
		super("RANK");
		this.list = list;
		
		JP panel = new JP();
		JButton btn = new JButton("CLOSE");
		
		btn.setLocation(155, 500);
		btn.setSize(90,30);
	
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panel.setLayout(null);
		panel.add(btn);
		add(panel);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	class JP extends JPanel{
		//SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd. hh:mm");
		public JP(){
			setPreferredSize(new Dimension(400, 550));
//			setBackground(Color.YELLOW);
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			ImageIcon bgimg = new ImageIcon("src/img/score.jpg");
		    g.drawImage(bgimg.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
//			g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 30));
//			g.drawString("TETRIS RANK", 110, 40);
//		    paintComponentString("TETRIS RANK", 110, 40, g, Color.DARK_GRAY, Color.white);
			g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
			g.setColor(Color.lightGray);
			g.drawString("RANK        DATE               USER         SCORE", 30, 100);

			g.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
			g.setColor(Color.white);
			for(int i=0;i<list.size();i++) {
				HashMap<String, Object> r= (HashMap<String, Object>) list.get(i);
				g.drawString(String.format("%3d",(i+1))+"st      "+r.get("reg_dt").toString().substring(0, 16),30,140+40*i);
				g.drawString(r.get("user_nm").toString(), 230, 140+40*i);
				g.drawString(String.format("%4s",r.get("score")), 330, 140+40*i);
			}
		}
}}
