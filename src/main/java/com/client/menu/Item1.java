package com.client.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.client.common.Block;
import com.client.common.GameFrame;

public class Item1 extends JMenuItem{
	public Item1() {
		super("NewGame");
		setMnemonic('n');
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Block.getInstance().gameOver();
				GameFrame.bgp.repaint();
			}
		});
	}
}
