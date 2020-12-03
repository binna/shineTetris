package com.client.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
			setBackground(Color.YELLOW);
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setFont(new Font("�޸տ�����", Font.PLAIN, 30));
			g.drawString("TETRIS RANK", 110, 40);
			g.setFont(new Font("�޸տ�����", Font.PLAIN, 20));
			g.setColor(Color.red);
			g.drawString("RANK        DATE             USER   SCORE", 8, 80);

			g.setFont(new Font("�޸տ�����", Font.PLAIN, 17));
			g.setColor(Color.black);
			for(int i=0;i<list.size();i++) {
				HashMap<String, Object> r= (HashMap<String, Object>) list.get(i);
				g.drawString(String.format("%3d",(i+1))+"st      "+r.get("reg_dt").toString().substring(0, 16),10,110+40*i);
				g.drawString(r.get("user_nm").toString(), 250, 110+40*i);
				g.drawString(String.format("%4s",r.get("score")), 340, 110+40*i);
			}
		}
}}
