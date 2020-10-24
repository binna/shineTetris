package com.client.menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.client.socket.RankSerialize;
import com.client.socket.SocketConnect;
import com.client.common.Block;
import com.client.common.GameFrame;

public class Item3 extends JMenuItem{
	public Item3() {
		super("Rank");
		setMnemonic('r');
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RankkingFrame(SocketConnect.getInstance().RankSearch());
				GameFrame.bgp.repaint();
			}
		});
	}
	
}
