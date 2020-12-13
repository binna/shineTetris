package com.client.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.client.socket.SocketConnect;
import com.client.common.GameFrame;

public class Item4 extends JMenuItem{
	public Item4() {
		super("Exit");
		setMnemonic('x');
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
			}
		});
	}

}
