package com.client.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.client.socket.SocketConnect;
import com.client.common.Block;
import com.client.common.GameFrame;

public class TetrisMenuBar extends JMenuBar{
	public JMenu menu1 = new JMenu("Menu");
	public TetrisMenuBar() {
		menu1.setMnemonic('m');

		menu1.add(new Item1());
		menu1.add(new Item2());
		menu1.add(new Item3());
		//menu1.add(new Item4());
		
		add(menu1);
		
		
	}
}
